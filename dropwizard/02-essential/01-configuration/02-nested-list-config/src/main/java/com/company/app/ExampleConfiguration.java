package com.company.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class ExampleConfiguration extends Configuration {
    @NotEmpty
    private List<Replicate> replicates;

    @JsonProperty
    public List<Replicate> getReplicates() {
        return replicates;
    }

    @JsonProperty
    public void setReplicates(List<Replicate> replicates) {
        this.replicates = replicates;
    }
}
