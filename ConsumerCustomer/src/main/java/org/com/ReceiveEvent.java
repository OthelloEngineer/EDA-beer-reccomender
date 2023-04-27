package org.com;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.com.events.CustomerPayload;

public class ReceiveEvent {
    public final CustomerPayload payload;

    private final Throwable error;

    private final ConsumerRecord<String, ?> record;

    private final String encodedValue;

    public ReceiveEvent(CustomerPayload payload, Throwable error, ConsumerRecord<String, ?> record, String encodedValue) {
        this.payload = payload;
        this.error = error;
        this.record = record;
        this.encodedValue = encodedValue;
    }

    public CustomerPayload getPayload() {
        return payload;
    }

    public Throwable getError() {
        return error;
    }

    public ConsumerRecord<String, ?> getRecord() {
        return record;
    }

    public String getEncodedValue() {
        return encodedValue;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public String toString() {
        return "ReceiveEvent{" +
                "payload=" + payload +
                ", error=" + error +
                ", record=" + record +
                ", encodedValue='" + encodedValue + '\'' +
                '}';
    }
}
