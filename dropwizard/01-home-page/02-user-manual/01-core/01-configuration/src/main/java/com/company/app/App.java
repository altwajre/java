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
        Map<String, String> messageQueue = config.getMessageQueue();
        System.out.println(messageQueue.get("host"));
    }
}
