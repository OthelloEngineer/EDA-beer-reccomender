package org.com;

import org.com.customer.TempKafkaAdmin;
import org.com.customer.producer.ProducerSendOne;
import org.com.customer.producer.ProducerSender;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       TempKafkaAdmin tempKafkaAdmin = new TempKafkaAdmin();
       tempKafkaAdmin.createCustomerTopic();
        System.out.println("Hello World!");
        ProducerSendOne producerSendOne = new ProducerSendOne(new ProducerSender("customer-test-event"));
        for (int i = 0; i < 900; i++) {
            producerSendOne.createOneCustomer();
        }
    }
}