package org.com.usersede;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.events.NewUser;

public class NewUserSerializer implements Serializer<NewUser> {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public byte[] serialize(String topic, NewUser data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
