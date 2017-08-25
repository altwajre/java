package com.company.app;

import com.codahale.metrics.health.HealthCheck;
import com.company.app.resources.ProductResource;
import com.company.app.resources.StatusResource;
import com.company.app.resources.TopicResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class DropwizardApp extends Application<Configuration> {
    @Override
    public void run(Configuration c, Environment e) throws Exception {
        e.jersey().register(new TopicResource());
        e.jersey().register(new ProductResource());
        e.jersey().register(new StatusResource());

        HealthCheck healthCheck = new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        };

        e.healthChecks().register("test", healthCheck);
    }
}