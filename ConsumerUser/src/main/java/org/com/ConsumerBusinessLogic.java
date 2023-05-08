package org.com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.com.database.DBservice;
import org.com.database.adapters.PostgrestQLAdapter;
import org.com.events.CreateUser;
import org.com.events.UserPayload;

public class ConsumerBusinessLogic {
    ObjectMapper objectMapper = new ObjectMapper();
    DBservice postgres = new PostgrestQLAdapter();
    public ConsumerBusinessLogic(EventReceiver receiver) {
        receiver.addListener(this::onEvent);
    }
    private void onEvent(ReceiveEvent event){
        if (!event.hasError()) {
            UserPayload payload = event.getPayload();
            delegateToDB(payload);
            try {
                System.out.println("Received: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload));

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Error: " + event.getError());
        }
    }

    private void delegateToDB(UserPayload payload) {
        try {
            ObjectNode node = objectMapper.readValue(objectMapper.writeValueAsString(payload), ObjectNode.class);
            if(node.get("type").asText().equals(CreateUser.TYPE)){
                postgres.saveUser((CreateUser) payload);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Delegating to DB: " + payload);
    }
}