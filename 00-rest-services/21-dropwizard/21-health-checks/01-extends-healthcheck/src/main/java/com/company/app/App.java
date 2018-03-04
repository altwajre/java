package com.company.app;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration> {
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        System.out.println("# App.run()");
        DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(new Database(true));
        environment.healthChecks().register("database", databaseHealthCheck);
    }

    public static void main(String... args) throws Exception {
        new App().run(args);
    }
}
