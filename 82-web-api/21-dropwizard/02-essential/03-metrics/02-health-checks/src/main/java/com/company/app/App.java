package com.company.app;

import com.codahale.metrics.health.HealthCheckRegistry;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class App extends Application<Configuration> {

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        System.out.println("#App.run()");

        HealthCheckRegistry registry = new HealthCheckRegistry();
        registry.register("database", new DatabaseHealthCheck(new Database()));

        registry.runHealthChecks().entrySet().forEach(entry -> {
            if(entry.getValue().isHealthy()){
                System.out.println(entry.getKey() + ": OK");
            }
            else{
                System.out.println(entry.getKey() + ": Fail");
            }
        });
    }
    public static void main(String... args) throws Exception {
        new App().run(args);
    }
}
