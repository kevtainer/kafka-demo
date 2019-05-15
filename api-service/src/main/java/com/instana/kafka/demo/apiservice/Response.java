package com.instana.kafka.demo.apiservice;

public class Response {

    private final long id;
    private final String response;

    public Response(long id, String response) {
        this.id = id;
        this.response = response;
    }

    public long getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }
}
