# Cassandra

```
$ cd /Users/whan/cassandra/apache-cassandra-3.9
$ bin/cassandra -f <- start cassandra in foreground
ctrl+c to shutdown
$ bin/cqlsh
cqlsh> SOURCE '~/mydir/file.cql' <- executes cql statements 
```

## SQL Code

webapi.cql

## Run the App

### Server

#### Intellij

> Launch App with Program args: server config.dev.yml

#### Command line

> `$ gradle clean run -PappArgs="['server','config.dev.yml']"`

### Client - cURL

#### Contact

**GET All**

> `$ curl http://localhost:8080/contacts`

```
[{"id":1,"name":"Tom"},{"id":2,"name":"Dick"},{"id":3,"name":"Harry"}]
```

**GET**

> `$ curl http://localhost:8080/contacts/2`

