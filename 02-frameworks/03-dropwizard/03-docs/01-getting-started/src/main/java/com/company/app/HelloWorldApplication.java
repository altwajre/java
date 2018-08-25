package com.company.app;

import com.company.app.health.TemplateHealthCheck;
import com.company.app.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration>
{
    public static void main( String[] args ) throws Exception {
        new HelloWorldApplication().run(new String[]{"server", "hello-world.yml"});
    }

    @Override
    public String getName(){
        return "hello-world";
    }

    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap){

    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        environment.jersey().register(healthCheck);
    }
}
