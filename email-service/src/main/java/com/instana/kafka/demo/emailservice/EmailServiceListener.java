package com.instana.kafka.demo.emailservice;

import com.instana.kafka.demo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EmailServiceListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceListener.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @KafkaListener(topics = "send-customer-email", groupId = "group-id")
    public void listener(Customer customer) {
        Map<String, String> actionmap = new HashMap<>();
        actionmap.put("id", customer.id);
        actionmap.put("email-to", String.format("%s %s <%s>", customer.firstName, customer.lastName, customer.email));
        actionmap.put("template", "customer-create");

        LOGGER.info("sending email transaction='{}'", actionmap.toString());
        rabbitTemplate.convertAndSend(AmpqConfiguration.MAIL_MESSAGE_QUEUE, actionmap);
    }
}
