package org.com.customer.producer;

import org.com.customer.events.UserPayload;
import org.apache.kafka.clients.producer.*;

import java.util.concurrent.Future;

public class ProducerSender implements UserEventSender {
    private final Producer<String, UserPayload> producer;
    private final String topic;

    public ProducerSender(String topic){
        this.topic = topic;
        UserProducerConfig config = new UserProducerConfig(topic);
        this.producer = new KafkaProducer<>(config.getProducerConfig());
    }

    @Override
    public Future<RecordMetadata> send(UserPayload payload) {
        final ProducerRecord<String, UserPayload> record = new ProducerRecord<>(topic, payload.id.toString(), payload);
        return producer.send(record);
    }

    @Override
    public void close() {
        producer.close();
    }
}