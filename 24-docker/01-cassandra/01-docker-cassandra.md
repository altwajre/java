# cassandra

> docker run

```
docker run --name cassandra-1-01 -d -p 9042:9042 cassandra:latest
```

> remote into docker

```
docker exec -it cassandra-1-01 sh
```

> cassandra - create table, insert record, select

```
# cqlsh
cqlsh> CREATE KEYSPACE testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
cqlsh> USE testdb;

CREATE TABLE IF NOT EXISTS Customer (
customer_id text PRIMARY KEY,
name text,
age smallint
);

INSERT INTO Customer (
customer_id,
name,
age
)
VALUES (
'123',
'Tom',
28
);

cqlsh:home_security> SELECT * FROM Customer;
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
