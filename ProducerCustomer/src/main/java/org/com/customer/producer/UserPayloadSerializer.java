package org.com.customer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.customer.events.UserPayload;
import org.apache.kafka.common.serialization.Serializer;

public class UserPayloadSerializer implements Serializer<UserPayload> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, UserPayload data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
