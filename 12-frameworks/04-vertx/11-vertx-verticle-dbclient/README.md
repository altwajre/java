# Rest with Vert.x

REST API

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
