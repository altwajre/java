# Communicating With Cassandra

https://docs.datastax.com/en/cql/3.3/cql/cqlIntro.html

```
~/cassandra/apache-cassandra-3.0.6$ bin/cqlsh
Connected to Test Cluster at localhost:9160.
[cqlsh 4.1.1 | Cassandra 3.0.6 | CQL spec 3.1.1 | Thrift protocol 19.39.0]
Use HELP for help.
cqlsh>
https://docs.datastax.com/en/cql/3.3/cql/cql_reference/describe_r.html
cqlsh> DESCRIBE Cluster
Cluster: Test Cluster
Partitioner: Murmur3Partitioner
Snitch: SimpleSnitch
cqlsh> HELP;
cqlsh> HELP CREATE_KEYSPACE;
```

