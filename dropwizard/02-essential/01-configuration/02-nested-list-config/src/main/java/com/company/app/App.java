package com.company.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

import java.util.List;

public class App extends Application<ExampleConfiguration>
{
    public static void main( String[] args ) throws Exception {
        new App().run(args);
    }

    @Override
    public void run(ExampleConfiguration config, Environment environment) throws Exception {
        List<Replicate> replicates = config.getReplicates();
        System.out.println(replicates.get(0).getSource());
        System.out.println(replicates.get(0).getDestination());
        System.out.println(replicates.get(0).getFields().get(0));
        System.out.println(replicates.get(0).getFields().get(1));
        System.out.println(replicates.get(0).getFields().get(2));

    }
}
