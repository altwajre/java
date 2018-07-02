package com.dwbook.phonebook;

import com.dwbook.phonebook.resources.ContactResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<PhonebookConfiguration>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b){}
    @Override
    public void run(PhonebookConfiguration c, Environment e) throws Exception{
        LOGGER.info("Method App#run() called");
        e.jersey().register(new ContactResource());
    }
    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }
}
