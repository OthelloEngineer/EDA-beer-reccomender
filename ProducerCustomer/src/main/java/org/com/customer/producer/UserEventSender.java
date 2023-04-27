package org.com.customer.producer;

import org.com.customer.events.UserPayload;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.Closeable;
import java.util.concurrent.Future;

public interface UserEventSender extends Closeable{
    Future<RecordMetadata> send(UserPayload payload);

    default RecordMetadata blockingSend(UserPayload payload){
        try {
            return send(payload).get();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    void close();
}
