package org.com;

import org.apache.kafka.clients.consumer.ConsumerConfig;

import java.time.Duration;
import java.util.Map;


public class RunConsumer {
    public static void main(String[] args) throws InterruptedException {
        final Map<String, Object> config = Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                ConsumerConfig.GROUP_ID_CONFIG, "user-consumer-group",
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"
        );
        try(var consumer = new UserConsumer(config, "user-test-event", Duration.ofMillis(10), 10)){
            new ConsumerBusinessLogic(consumer);
            consumer.start();
            Thread.sleep(10000);
        }
    }
}
