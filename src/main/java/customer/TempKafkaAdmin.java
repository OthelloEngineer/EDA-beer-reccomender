package customer;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TempKafkaAdmin {
    Admin admin;

    public TempKafkaAdmin() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        this.admin = Admin.create(properties);
    }

    public void createCustomerTopic() throws ExecutionException, InterruptedException {
        int partions = 3;
        short replicationFactor = 1;
        String topic = "customer-test-event";
        NewTopic newTopic = new NewTopic(topic, partions, replicationFactor);
        CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));
        System.out.println(result.values().keySet());
        KafkaFuture<Void> future = result.values().get(topic);
        admin.close();
    }

}
