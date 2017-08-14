# Vert.x Application Configuration

http://vertx.io/blog/vert-x-application-configuration/

> Run App with `-config`

```
$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json
```

## Application

> config

`src/main/conf/conf.json`

```
{
  "http.port": 8082
}
```

> Compile

```
$ mvn clean package
```

> Run App with -config

```
$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json
```

curl http://localhost:8082/
