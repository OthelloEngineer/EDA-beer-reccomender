module ProducerCustomer {
    requires com.fasterxml.jackson.annotation;
    requires kafka.clients;
    requires com.fasterxml.jackson.databind;
    exports org.com.customer.producer;
    exports org.com.customer.events;
}