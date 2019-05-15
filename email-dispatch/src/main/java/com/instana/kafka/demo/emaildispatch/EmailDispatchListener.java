package com.instana.kafka.demo.emaildispatch;

import com.instana.kafka.demo.EmailRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class EmailDispatchListener {
    private static final Logger log = LogManager.getLogger(EmailDispatchListener.class);

    @Autowired
    private EmailRecordRepository repository;
    /**
     * This method is invoked whenever any new message is put in the queue.
     * See {@link guru.springframework.SpringBootRabbitMQApplication} for more details
     * @param message
     */
    public void receiveMessage(Map<String, String> message) throws Exception {
        log.info("Received <" + message + ">");

        log.info("Message processed...");

        EmailRecord record = new EmailRecord(message.get("email-to"), processTemplate(message.get("template")), Instant.now().toString()  );
        repository.save(record);
    }

    protected String processTemplate(String templateId) throws Exception {
        String responseBody;

        switch (templateId) {
            case "customer-create":
                responseBody = String.format("Hello! Welcome to the Instana Customer Demo!");
                break;
            default:
                throw new Exception("oh noes!");
        }

        return responseBody;
    }
}
