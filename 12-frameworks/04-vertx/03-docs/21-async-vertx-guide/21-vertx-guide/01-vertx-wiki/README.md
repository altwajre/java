# vertx wiki

## hsqldb

location: /Users/whan/hsqldb-2.4.0/hsqldb

> start database

```
java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 wiki
```

## MainVerticle

> start verticle

```
mvn clean package exec:java
```

1. http://localhost:8080/
2. Enter `foo`, click `Create` button

## App

> start app to verify the data in database
