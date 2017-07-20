package com.company.app

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration

class EcommConfiguration extends Configuration {
    @JsonProperty
    DataSourceConfig dataSourceConfig;
}
