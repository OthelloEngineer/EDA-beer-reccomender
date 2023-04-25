package org.com.customer.producer;

import org.com.customer.events.CustomerPayload;
import org.apache.kafka.clients.producer.*;

import java.util.concurrent.Future;

public class ProducerSender implements CustomerEventSender{
    private final Producer<String, CustomerPayload> producer;
    private final String topic;

    public ProducerSender(String topic){
        this.topic = topic;
        CustomerProducerConfig config = new CustomerProducerConfig(topic);
        this.producer = new KafkaProducer<>(config.getProducerConfig());
    }

    @Override
    public Future<RecordMetadata> send(CustomerPayload payload) {
        final ProducerRecord<String, CustomerPayload> record = new ProducerRecord<>(topic, payload.id.toString(), payload);
        return producer.send(record);
    }

    @Override
    public void close() {
        producer.close();
    }
}