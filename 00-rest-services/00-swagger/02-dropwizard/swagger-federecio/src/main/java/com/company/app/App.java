package com.company.app;

import com.company.app.resource.ContactsResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App extends Application<Configuration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String... args) throws Exception {
        new App().run("server");
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap){
        bootstrap.addBundle(new SwaggerBundle<Configuration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(Configuration configuration) {
                SwaggerBundleConfiguration config = new SwaggerBundleConfiguration();
                config.setResourcePackage("com.company.app.resource");
                return config;
            }
        });
    }

    @Override
    public void run(Configuration configuration, Environment e) throws Exception {
        // Add the resource to the environment
        e.jersey().register(new ContactsResource());

    }

}
