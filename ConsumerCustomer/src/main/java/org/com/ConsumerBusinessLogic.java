package org.com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.EventReceiver;
import org.com.ReceiveEvent;
import org.com.events.CustomerPayload;

public class ConsumerBusinessLogic {
    ObjectMapper objectMapper = new ObjectMapper();
    public ConsumerBusinessLogic(EventReceiver receiver) {
        receiver.addListener(this::onEvent);
    }
    private void onEvent(ReceiveEvent event){
        if (!event.hasError()) {
            CustomerPayload payload = event.getPayload();
            try {
                System.out.println("Received: " + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Error: " + event.getError());
        }
    }
}
