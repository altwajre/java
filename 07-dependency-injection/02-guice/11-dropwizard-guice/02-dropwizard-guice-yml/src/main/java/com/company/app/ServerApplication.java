package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.validator.constraints.NotEmpty;

class ServerConfiguration extends Configuration {
    @NotEmpty
    private String message;
    public String getMessage(){
        return message;
    }
}
class ServerModule extends AbstractModule {
    @Override
    protected void configure() { }

    @Provides
    @Named("message")
    public String provideMessage(ServerConfiguration serverConfiguration){
        return serverConfiguration.getMessage();
    }
}
public class ServerApplication extends Application<ServerConfiguration> {

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        GuiceBundle<ServerConfiguration> guiceBundle = GuiceBundle.<ServerConfiguration>newBuilder()
                .addModule(new ServerModule())
                .setConfigClass(ServerConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(ServerConfiguration serverConfiguration, Environment environment) throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new ServerApplication().run(args);
    }
}
