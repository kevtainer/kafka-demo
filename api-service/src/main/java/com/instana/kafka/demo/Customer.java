package com.instana.kafka.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.kafka.annotation.KafkaListener;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @Id
    public String id;

    public String firstName;
    public String lastName;
    public String email;

    public Customer(
            @JsonProperty("first_name") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s', email='%s']",
                id, firstName, lastName, email);
    }
}
