package org.com.customer.producer;

import org.com.customer.events.CreateUser;
import org.com.customer.events.UserPayload;

import java.util.UUID;

public class ProducerSendOne {
    private final UserEventSender sender;

    public ProducerSendOne(UserEventSender sender) {
        this.sender = sender;
    }
    public void createOneCustomer(){
        UserPayload payload = new CreateUser.Builder(UUID.randomUUID())
                .age(18)
                .country("Danmark")
                .zip("5220")
                .city("Odense")
                .address("Vejnavn 1")
                .phone("12345678")
                .email("ajokjoa@kaokao.com")
                .name("Jens Jensen")
                .build();
        sender.blockingSend(payload);
    }
}