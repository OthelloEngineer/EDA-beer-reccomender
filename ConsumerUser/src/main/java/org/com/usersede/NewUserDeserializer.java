package org.com.usersede;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.events.NewUser;

import java.io.IOException;

public class NewUserDeserializer implements Deserializer<NewUser> {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public NewUser deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, NewUser.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
