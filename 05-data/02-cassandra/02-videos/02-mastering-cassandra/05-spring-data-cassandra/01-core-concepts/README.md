# The Core Concepts

## Start cassandra

```
$ cd /Users/whan/cassandra/apache-cassandra-3.0.6
$ bin/cassandra -f <- start cassandra in foreground
ctrl+c to shutdown
$ bin/cqlsh
```

## if you haven't create cwt.member, CREATE database, table, insert

```
CREATE KEYSPACE cwt WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};
use cwt;
CREATE TABLE cwt.member (
       ... username text PRIMARY KEY,
       ... active text,
       ... email text,
       ... fullname text,
       ... password text,
       ... role text);
insert into member (username, email, password, fullname) values ('kan', 'kan@gmail.com', '12345', 'CY Ken');
select * from member;
```

