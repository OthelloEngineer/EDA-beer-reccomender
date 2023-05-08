package org.com.usersede;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.com.events.NewUser;

public class UserSerde implements Serde<NewUser> {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Serializer<NewUser> serializer() {
        return new NewUserSerializer();
    }

    @Override
    public Deserializer<NewUser> deserializer() {
        return new NewUserDeserializer();
    }
}
