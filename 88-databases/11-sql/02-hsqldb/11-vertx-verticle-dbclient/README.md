# Vert.x REST hsqldb

REST API

## Run Database

> location

/Users/whan/hsqldb-2.4.0/hsqldb

> start database

```
$ java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb
```

> database manager

```
$ java -cp lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
```

URL: jdbc:hsqldb:hsql:localhost/testdb

> Compile and Run

```
$ mvn clean package

$ java -jar target/vertx-dbclient-1.0-SNAPSHOT.jar
```

http://localhost:8080/

http://localhost:8080/api/customers

> CURL - POST

```
curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"name": "Tom", "age": "18"}'
```

> CURL - GET all

```
curl http://localhost:8080/api/customers
```
