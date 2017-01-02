package com.company.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class Replicate {
    @NotEmpty
    private String source;
    @NotEmpty
    private String destination;
    @NotEmpty
    private List<String> fields;
    @JsonProperty
    public String getSource() { return source; }
    @JsonProperty
    public void setSource(String source) {
        this.source = source;
    }
    @JsonProperty
    public String getDestination() { return destination; }
    @JsonProperty
    public void setDestination(String destination) {
        this.destination = destination;
    }
    @JsonProperty
    public List<String> getFields() { return fields; }
    @JsonProperty
    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
