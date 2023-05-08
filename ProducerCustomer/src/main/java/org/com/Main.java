package org.com;

import org.com.customer.TempKafkaAdmin;
import org.com.customer.producer.ProducerSendOne;
import org.com.customer.producer.ProducerSender;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
       TempKafkaAdmin tempKafkaAdmin = new TempKafkaAdmin();
       tempKafkaAdmin.createCustomerTopic();
        ProducerSendOne producerSendOne = new ProducerSendOne(new ProducerSender("user-test-event"));
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            producerSendOne.createOneCustomer();
        }
        long end = System.currentTimeMillis();
        System.out.println("Time: " + (end - start));
    }
}