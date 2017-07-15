package com.company.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

public class ExampleConfiguration extends Configuration {
    @NotEmpty
    private String name;
    @NotEmpty
    private Map<String, String> messageQueue;
    @JsonProperty("messageQueue")
    public Map<String, String> getMessageQueue() {
        return messageQueue;
    }
    @JsonProperty("messageQueue")
    public void setMessageQueue(Map<String, String> messageQueue) {
        this.messageQueue = messageQueue;
    }
    @JsonProperty
    public String getName() { return name; }
    @JsonProperty
    public void setName(String name) { this.name = name; }
}
