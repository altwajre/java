package com.company.app;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {
    private final Database database;

    public DatabaseHealthCheck(Database database) {
        this.database = database;
    }

    @Override
    protected Result check() throws Exception {
        if(!this.database.isConnected()){
            return Result.unhealthy("database is not connected");
        }
        return Result.healthy();
    }
}
