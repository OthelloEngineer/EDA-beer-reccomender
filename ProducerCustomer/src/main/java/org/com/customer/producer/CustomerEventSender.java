package org.com.customer.producer;

import org.com.customer.events.CustomerPayload;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.Closeable;
import java.util.concurrent.Future;

public interface CustomerEventSender extends Closeable{
    Future<RecordMetadata> send(CustomerPayload payload);

    default RecordMetadata blockingSend(CustomerPayload payload){
        try {
            return send(payload).get();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    void close();
}
