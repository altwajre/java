package com.company.server;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<ServerConfiguration> {
    public static void main(String[] args) throws Exception{
        new ServerApplication().run(args);
    }
    @Override
    public void run(ServerConfiguration configuration, Environment environment) throws Exception {
        System.out.println("$$ run()");
        System.out.println(configuration.getName());
        System.out.println(configuration.getAge());
    }
}
