package com.company.app;

import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<EcommConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<EcommConfiguration> bootstrap) {
        GuiceBundle<EcommConfiguration> guiceBundle = GuiceBundle.<EcommConfiguration>newBuilder()
                .addModule(new EcommModule())
                .setConfigClass(EcommConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(EcommConfiguration c, Environment e) throws Exception {
//        DataSourceConfig dataSourceConfig = c.getDataSourceConfig();
//
//        // Add the resource to the environment
//        e.jersey().register(new ContactsResource(dataSourceConfig));
//        e.jersey().register(new CarsResource(dataSourceConfig));
    }

}
