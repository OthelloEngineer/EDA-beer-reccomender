package org.com.customer.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class UserProducerConfig {
    private final String topic;
    private final String bootstrapServers;

    public UserProducerConfig(String topic) {
        this.topic = topic;
        this.bootstrapServers = "localhost:9092";
    }

    public Map<String, Object> getProducerConfig() {
        HashMap<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserPayloadSerializer.class.getName());
        return config;
    }
}
