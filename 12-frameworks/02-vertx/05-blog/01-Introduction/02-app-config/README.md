# Vert.x Application Configuration

http://vertx.io/blog/vert-x-application-configuration/

> Run with `-config`

```
$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json
```

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
