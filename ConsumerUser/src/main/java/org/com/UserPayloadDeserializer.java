package org.com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.events.UserPayload;

public class UserPayloadDeserializer implements Deserializer<UserPayload> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public UserPayload deserialize(String topic, byte[] data) {
        final String value = new String(data);
        try {
            final UserPayload payload = objectMapper.readValue(value, UserPayload.class);
            return payload;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
