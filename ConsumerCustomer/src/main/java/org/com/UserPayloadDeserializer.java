package org.com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.events.CustomerPayload;

public class UserPayloadDeserializer implements Deserializer<CustomerPayload> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public CustomerPayload deserialize(String topic, byte[] data) {
        final String value = new String(data);
        try {
            final CustomerPayload payload = objectMapper.readValue(value, CustomerPayload.class);
            return payload;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
