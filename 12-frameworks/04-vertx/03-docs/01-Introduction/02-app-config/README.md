# Verticle app with test

http://vertx.io/blog/my-first-vert-x-3-application/

## Application

> config

add src/main/conf/conf.json

```
{
  "http.port": 8082
}
```

> Compile

```
$ mvn clean package
```

> Run with -config

```
$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json
```

go to http://localhost:8082/
