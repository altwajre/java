# Customer API

# docker build and docker run

> build vertx project to generate the .jar file

$ mvn clean package

> docker build and docker run

docker build -t verticle-service .
docker run --name verticle-service --network=local-services -d -p 8080:8080 verticle-service

> cleanup

- Force delete all containers
docker rm -f $(docker ps -a -q)

- Force delete all images
docker rmi -f $(docker images -q)

## Launch App

> command line

$ mvn clean package
$ java -jar target/vertx-1.0-SNAPSHOT.jar

> intellij

App.main()

> test

```
curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"id": "3", "name": "Harry", "age": "38"}'
{
  "id" : 3,
  "name" : "Harry",
  "age" : 38
}

curl http://localhost:8080/api/customers
[ {
  "id" : 1,
  "name" : "Tom",
  "age" : 18
}, {
  "id" : 2,
  "name" : "Dick",
  "age" : 28
}, {
  "id" : 3,
  "name" : "Harry",
  "age" : 38
}
```
