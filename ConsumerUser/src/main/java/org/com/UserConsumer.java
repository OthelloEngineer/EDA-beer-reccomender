package org.com;

import com.obsidiandynamics.worker.WorkerOptions;
import com.obsidiandynamics.worker.WorkerThread;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.events.UserPayload;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.*;

public class UserConsumer extends AbstractReceiver{
    long count;
    long start;
    long end;
    private final WorkerThread pollingThread;
    private final WorkerThread processingThread;
    private final Consumer<String, UserPayload> consumer;
    private final Duration pollTimeout;
    private final BlockingQueue<ReceiveEvent> receivedEvents;
    private final Queue<Map<TopicPartition, OffsetAndMetadata>> pendingOffsets;

    public UserConsumer(Map<String, Object> consumerConfig, String topic, Duration pollTimeout, int queueCapacity){
        this.pollTimeout = pollTimeout;
        this.receivedEvents = new LinkedBlockingQueue<>(queueCapacity);
        this.pendingOffsets = new LinkedBlockingQueue<>();

        final var mergedConfig = new HashMap<>(consumerConfig);
        mergedConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        mergedConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserPayloadDeserializer.class.getName());
        mergedConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        this.consumer = new KafkaConsumer<>(mergedConfig);
        this.consumer.subscribe(Set.of(topic));


        this.pollingThread = WorkerThread.builder()
                .withOptions(new WorkerOptions().daemon().withName(UserConsumer.class, "polling"))
                .onCycle(this::onPollCycle)
                .build();
        this.processingThread = WorkerThread.builder()
                .withOptions(new WorkerOptions().daemon().withName(UserConsumer.class, "processing"))
                .onCycle(this::onProcessCycle)
                .build();
    }

    private void onPollCycle(WorkerThread thread) throws InterruptedException {
        final ConsumerRecords<String, UserPayload> records = consumer.poll(pollTimeout);
        for (var record : records) {
            if (count == 0){
                start = System.currentTimeMillis();
            }
            count++;
            if(count == 900){
                end = System.currentTimeMillis();
                System.out.println("Time taken to close consumer: " + (end - start) + "ms");
            }
            System.out.println(count);
            final UserPayload value = record.value();
            final var event = new ReceiveEvent(value, null, record, record.value().toString());
            receivedEvents.put(event);
        }
        for (Map<TopicPartition, OffsetAndMetadata> pendingOffset;
             (pendingOffset = pendingOffsets.poll()) != null;) {
            consumer.commitAsync(pendingOffset, null);
        }
    }

    private void onProcessCycle(WorkerThread thread) throws InterruptedException {
        final ReceiveEvent event = receivedEvents.take();
        super.fire(event);
        final Map<TopicPartition, OffsetAndMetadata> pendingOffset = new HashMap<>();
        final ConsumerRecord<String, ?> record = event.getRecord();
        pendingOffset.put(new TopicPartition(record.topic(), record.partition()),
                new OffsetAndMetadata(record.offset() + 1));
        pendingOffsets.add(pendingOffset);
        super.fire(event);
    }

    @Override
    public void start() {
        pollingThread.start();
        processingThread.start();
    }

    @Override
    public void close() {
        pollingThread.terminate().joinSilently();
        processingThread.terminate().joinSilently();
        consumer.close();
    }
}
