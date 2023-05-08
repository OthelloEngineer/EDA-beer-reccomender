package org.com;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.com.events.CreateUser;
import org.com.events.NewUser;
import org.com.events.UserPayload;
import org.com.usersede.UserSerde;

import java.util.Objects;
import java.util.Properties;

public class UserStreamConsumer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "user-stream-consumer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, UserPayloadSerdeDeserializer.class);
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, UserPayload> stream = builder.stream("user-test-event");
        stream.filter((key, value) -> value.getType().equals(CreateUser.TYPE))
                .mapValues(value -> new NewUser(((CreateUser)value).name,((CreateUser)value).email,
                        ((CreateUser)value).country))
                .peek((key, value) -> System.out.println("key: " + key + " value: " + value))
                .to("new-user", Produced.with(Serdes.String(), new UserSerde()));
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        long start = System.currentTimeMillis();
        streams.start();
        long end = System.currentTimeMillis();
        System.out.println("Time taken to close consumer: " + (end - start) + "ms");
    }
}