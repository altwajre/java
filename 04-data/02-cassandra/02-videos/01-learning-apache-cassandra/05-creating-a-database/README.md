# Creating A Database

```
~/cassandra/apache-cassandra-2.0.7$ bin/cqlsh
cqlsh>
cqlsh> DESCRIBE KEYSPACES; <- list all databases
system  system_traces
cqlsh> DESCRIBE KEYSPACE system; <- look into the system database
cqlsh> CREATE KEYSPACE vehicle_tracker WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}; <- create database
cqlsh> DESCRIBE KEYSPACES;
system  vehicle_tracker  system_traces
cqlsh> DESCRIBE KEYSPACE vehicle_tracker;
CREATE KEYSPACE vehicle_tracker WITH replication = {
  'class': 'SimpleStrategy',
  'replication_factor': '1'
};
cqlsh> DROP KEYSPACE vehicle_tracker; <- delete database: recreate the vehicle_tracker if you deleted it

```

## Lab

```
cqlsh> CREATE KEYSPACE home_security WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}; <- create home_security database
cqlsh> DESCRIBE KEYSPACES;
system  vehicle_tracker  home_security  system_traces
cqlsh> DESCRIBE KEYSPACE home_security;
CREATE KEYSPACE home_security WITH replication = {
  'class': 'SimpleStrategy',
  'replication_factor': '1'
};
```
