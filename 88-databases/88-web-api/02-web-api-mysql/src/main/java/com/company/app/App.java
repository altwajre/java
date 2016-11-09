package com.company.app;

import com.company.app.resources.ContactsResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<Configuration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(Configuration configuration, Environment e) throws Exception {
        // Add the resource to the environment
        e.jersey().register(new ContactsResource());

    }

}
