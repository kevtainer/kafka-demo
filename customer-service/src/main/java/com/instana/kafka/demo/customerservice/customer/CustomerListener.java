package com.instana.kafka.demo.customerservice.customer;

import com.instana.kafka.demo.Customer;
import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
public class CustomerListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private CustomerRepository repository;

    @KafkaListener(topics = "create-customer-record", groupId = "group-id")
    public void listener(Customer customer) {
        LOGGER.info("saving customer='{}'", customer.toString());
        repository.save(customer);
    }
}
