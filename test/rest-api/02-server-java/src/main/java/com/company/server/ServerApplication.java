package com.company.server;

import com.company.server.resources.ServerResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<ServerConfiguration> {
    public static void main(String[] args) throws Exception{
        new ServerApplication().run(args);
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        final ServerResource resource = new ServerResource(
                configuration.getName()
        );
        environment.jersey().register(resource);
    }
}
