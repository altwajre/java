package com.company.app.server;

import com.company.app.server.resources.ContactResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class DropwizardApp extends Application<Configuration> {
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        e.jersey().register(new ContactResource());
    }
}