package org.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.com.events.UserPayload;

public class UserPayloadSerdeDeserializer implements Serde<UserPayload> {

    @Override
    public Serializer<UserPayload> serializer() {
        return new UserPayloadSerializer();
    }

    @Override
    public Deserializer<UserPayload> deserializer() {
        return new UserPayloadDeserializer();
    }
}
