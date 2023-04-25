package customer.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import customer.events.CustomerPayload;
import org.apache.kafka.common.serialization.Serializer;

public class CustomerPayloadSerializer implements Serializer<CustomerPayload> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, CustomerPayload data) {
        try {
            System.out.println("Serializing data: " +
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data) + " to topic: " + topic);
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
