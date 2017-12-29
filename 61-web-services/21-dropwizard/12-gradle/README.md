# extends HealthCheck example

http://www.dropwizard.io/1.0.3/docs/getting-started.html#creating-a-health-check

## Code

1, Create Health-Check target

```
public class Database {

    public Database(boolean isConnected){
        this.isConnected = isConnected;
    }

    private boolean isConnected;

    public boolean isConnected() {
        return isConnected;
    }
}
```

2, Create Health-Check class

```
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
```

3, Register Health-Check

```
public class App extends Application<Configuration> {
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(new Database(true));
        environment.healthChecks().register("database", databaseHealthCheck);
    }
}
```

## Run the App

### Intellij

#### Server

Launch App with Program args: server

#### Client - cURL

> `$ curl http://localhost:8081/healthcheck`

```
{"database":{"healthy":true},"deadlocks":{"healthy":true}}
```
