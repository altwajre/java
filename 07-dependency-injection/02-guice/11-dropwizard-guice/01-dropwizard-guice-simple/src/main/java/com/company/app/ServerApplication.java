package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

class ServerModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Named("message")
    public String provideMessage() {
        return "Hello World!";
    }

    @Provides
    @Named("name")
    public String providerName() {
        return "Tom";
    }
}

public class ServerApplication extends Application<Configuration> {

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {
        GuiceBundle<Configuration> guiceBundle = GuiceBundle.<Configuration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(Configuration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {

    }

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(new String[]{"server"});
    }
}
