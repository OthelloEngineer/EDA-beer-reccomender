package customer.producer;

import customer.events.CreateCustomer;
import customer.events.CustomerPayload;

import java.util.UUID;

public class ProducerSendOne {
    private final CustomerEventSender sender;

    public ProducerSendOne(CustomerEventSender sender) {
        this.sender = sender;
    }
    public void createOneCustomer(){
        CustomerPayload payload = new CreateCustomer.Builder(UUID.randomUUID())
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