# Installing Cassandra - ubuntu; mac is similar

```
$ cd ~
$ mkdir cassandra
$ cd cassandra/
$ mv ~/Downloads/apache-cassandra-3.0.6-bin.tar.gz .
$ tar -xvzf apache-cassandra-3.0.6-bin.tar.gz  <- unzip
$ cd ~/cassandra/apache-cassandra-3.0.6 <- location
$ sudo mkdir /var/lib/cassandra
$ sudo mkdir /var/log/cassandra
$ sudo chown -R $USER:$GROUP /var/lib/cassandra
$ sudo chown -R $USER:$GROUP /var/log/cassandra
$ bin/cassandra -f <- start cassandra in foreground
ctrl+c to shutdown
~/cassandra/apache-cassandra-3.0.6$ bin/nodetool status
Datacenter: datacenter1
=======================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address    Load       Owns (effective)  Host ID                               Token                                    Rack
UN  127.0.0.1  56.65 KB   100.0%            862fa569-465c-484a-b85d-d03d7a8be6df  -9169960011806142685                     rack1
~/cassandra/apache-cassandra-3.0.6$ bin/nodetool info -h 127.0.0.1
Token            : (invoke with -T/--tokens to see all 256 tokens)
ID               : 862fa569-465c-484a-b85d-d03d7a8be6df
Gossip active    : true
Thrift active    : true
Native Transport active: true
Load             : 66.32 KB
Generation No    : 1461428008
Uptime (seconds) : 103
Heap Memory (MB) : 34.44 / 736.00
Data Center      : datacenter1
Rack             : rack1
Exceptions       : 0
Key Cache        : size 736 (bytes), capacity 37748736 (bytes), 69 hits, 77 requests, 0.896 recent hit rate, 14400 save period in seconds
Row Cache        : size 0 (bytes), capacity 0 (bytes), 0 hits, 0 requests, NaN recent hit rate, 0 save period in seconds
$ cd /var/log/cassandra/  <- in a new terminal
$ ls
$ vim system.log
~/cassandra/apache-cassandra-3.0.6$ cd conf
~/cassandra/apache-cassandra-3.0.6$ vim log4j-server.properties
```

```
$ bin/cassandra <- start cassandra in background
$ ps aux | grep cass  <- kill cassandra other terminal
vm1    16164
$ kill 16164
```
