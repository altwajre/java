package com.company.server;

import io.dropwizard.Configuration;

public class ServerConfiguration extends Configuration {
    private String template;
    public String getTemplate()
    {
        return template;
    }
    public void setTemplate(String template)
    {
        this.template = template;
    }
}
