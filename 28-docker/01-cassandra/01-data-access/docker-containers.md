# docker containers

User Defined Bridge Networks - `--network=local-services`

## docker-container cassandra

> docker run

```
docker run --name cassandra-01 --network=local-services -d -p 9042:9042 cassandra:latest
```

> remote into docker

```
docker exec -it cassandra-01 sh
```

## docker-container data-access services

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

> cleanup

```
# List all running containers
docker ps
# List all containers includes stopped ones
docker ps -a
# List all images
docker images

# Delete all containers
docker rm $(docker ps -a -q)
# Force delete all containers
docker rm -f $(docker ps -a -q)

# Delete all images
docker rmi $(docker images -q)
# Force delete all images
docker rmi -f $(docker images -q)

# Delete a container
docker rm myservice
# Delete an image
docker rmi myservice
```
