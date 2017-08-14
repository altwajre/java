# product service

> jar

Use `maven-shade-plugin` to create fat jar

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

> Run App with `-config`

```
$ java -jar target/app-config-1.0-SNAPSHOT.jar -conf src/main/conf/conf.json
```

curl http://localhost:8082/api/products

```
[ {
  "id" : 0,
  "name" : "iPhone"
}, {
  "id" : 1,
  "name" : "MacBook"
} ]
```

> Run Test - `mvn clean test`

`ProductVerticleTest`

```
Running com.company.app.ProductVerticleTest
8081
[ {
  "id" : 0,
  "name" : "iPhone"
}, {
  "id" : 1,
  "name" : "MacBook"
} ]
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 1.124 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

## Docker - manual steps

> build image

```
$ docker build -t local-product .
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED              SIZE
local-product      latest              26ea0a1d2895        About a minute ago   319MB
java                8-jre               e44d62cf8862        6 months ago         311MB
```

> run container

```
                host:container
$ docker run -p 8081:8080 -d local-product
5295f1b99e80582e130ce3622dde7919d2cdbc213af8c52f5f150c85141e8210
$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                    NAMES
5295f1b99e80        local-product      "sh -c 'exec java ..."   41 seconds ago      Up 42 seconds       0.0.0.0:8081->8080/tcp   zealous_galileo
```

> test

curl http://localhost:8081/api/products

```
[
{
id: 0,
name: "iPhone"
},
{
id: 1,
name: "MacBook"
}
]
```

> cleanup

```
docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)
```
