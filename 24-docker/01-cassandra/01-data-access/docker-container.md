# docker containers

## docker-container cassandra

> docker run

```
docker run --name cassandra-01 --network=local-services -d -p 9042:9042 cassandra:latest
```

> remote into docker

```
docker exec -it cassandra-01 sh
```

## docker-container data-access

> code

address = "cassandra-01" because of `docker run -name cassandra-01`

```
String address = "cassandra-01"; // docker container
Cluster cluster = Cluster.builder().addContactPoint(address)
    .build();
```

> docker build and docker run

```
docker build -t data-access .

docker run --name data-access-01 --network=local-services data-access
```
