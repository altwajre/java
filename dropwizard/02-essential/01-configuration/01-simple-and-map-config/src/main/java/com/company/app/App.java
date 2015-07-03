package com.company.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.Map;

public class App extends Application<ExampleConfiguration>
{
    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(ExampleConfiguration config, Environment environment) throws Exception {
        System.out.println(config.getName());  // Simple config
        Map<String, String> messageQueue = config.getMessageQueue();  // Map config
        System.out.println(messageQueue.get("host"));
    }
}
