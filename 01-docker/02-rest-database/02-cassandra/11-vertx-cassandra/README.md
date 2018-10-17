# docker containers

User Defined Bridge Networks - `--network=local-services`

## docker-container cassandra

> docker run

docker run --name cassandra-01 --network=local-services -d -p 9042:9042 cassandra:latest

> remote into docker

docker exec -it cassandra-01 sh

## docker-container data-access services

> code

address = "cassandra-01" because of `docker run -name cassandra-01`

```
String address = "cassandra-01"; // docker container
Cluster cluster = Cluster.builder().addContactPoint(address)
    .build();
```

> build vertx project to generate the .jar file

$ mvn clean package

> docker build and docker run

docker build -t verticle-service .
docker run --name verticle-service-01 --network=local-services -d -p 8081:8080 verticle-service

> cleanup

- Force delete all containers
docker rm -f $(docker ps -a -q)

- Force delete all images
docker rmi -f $(docker images -q)
