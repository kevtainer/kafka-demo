package com.instana.kafka.demo.apiservice.customer;

import com.instana.kafka.demo.Customer;
import com.instana.kafka.demo.apiservice.Response;
import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);


    @Autowired
    private CustomerRepository repository;

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/customer/create")
    public Response work(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName,
            @RequestParam(value = "email") String email) {

        Customer customer = new Customer(firstName, lastName, email);
        LOGGER.info("producing customer record: '{}'", customer.toString());

        kafkaTemplate.send("create-customer-record", customer);
        kafkaTemplate.send("send-customer-email", customer);

        return new Response(counter.incrementAndGet(), "data sent to producer");
    }

    @RequestMapping("/customer")
    public java.util.List getCustomer(
            @RequestParam(value = "firstName", required=false) String firstName) {
        return repository.findAll();
    }

}
