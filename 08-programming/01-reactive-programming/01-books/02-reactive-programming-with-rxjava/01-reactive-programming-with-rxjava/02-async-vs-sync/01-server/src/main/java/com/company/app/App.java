package com.company.app;

import com.company.app.dao.ContactDao;
import com.company.app.dao.ContactDaoImpl;
import com.company.app.model.Contact;
import com.company.app.resource.ContactResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws Exception {
        new App().run("server");
    }

    @Override
    public void run(Configuration configuration, Environment e) throws Exception {
        // Add the resource to the environment
        e.jersey().register(new ContactResource());

    }

}
