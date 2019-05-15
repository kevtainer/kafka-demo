package com.instana.kafka.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class EmailRecord {
    @Id
    public String id;

    public String mailTo;
    public String body;
    public String timestamp;

    public EmailRecord(String mailTo, String body, String timestamp) {
        this.mailTo = mailTo;
        this.body = body;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "EmailRecord[id=%s, mailTo='%s', body='%s', timestamp='%s']",
                id, mailTo, body, timestamp);
    }
}
