import customer.TempKafkaAdmin;
import customer.events.CreateCustomer;
import customer.producer.CustomerEventSender;
import customer.producer.ProducerSendOne;
import customer.producer.ProducerSender;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //TempKafkaAdmin tempKafkaAdmin = new TempKafkaAdmin();
        //tempKafkaAdmin.createCustomerTopic();
        System.out.println("Hello World!");
        ProducerSendOne producerSendOne = new ProducerSendOne(new ProducerSender("customer-test-event"));
        producerSendOne.createOneCustomer();
    }
}