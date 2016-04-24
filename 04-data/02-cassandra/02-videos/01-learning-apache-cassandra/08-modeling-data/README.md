# Modeling Data

```
vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ bin/cqlsh
Connected to Test Cluster at localhost:9160.
[cqlsh 4.1.1 | Cassandra 2.0.7 | CQL spec 3.1.1 | Thrift protocol 19.39.0]
Use HELP for help.
cqlsh> USE home_security;
cqlsh:home_security> SELECT * FROM activity WHERE home_id = 'H01474777';
cqlsh:home_security> SELECT * FROM activity WHERE home_id = 'H01474777' AND datetime > '2014-05-22 00:00:00';
cqlsh:home_security> SELECT * FROM activity WHERE home_id = 'H01474777' AND code_used = '5599';
Bad Request: No indexed columns present in by-columns clause with Equal operator
```

### Creating a Secondary Index

```
cqlsh:home_security> CREATE INDEX code_used_index ON activity (code_used);
cqlsh:home_security> SELECT * FROM activity WHERE home_id = 'H01474777' AND code_used = '5599';
```

### Defining a Composite Partition Key

```
cqlsh:vehicle_tracker> CREATE TABLE location (vehicle_id text, date text, time timestamp, latitude double, longitude double, PRIMARY KEY ((vehicle_id, date), time)) WITH CLUSTERING ORDER BY (time DESC);
cqlsh:vehicle_tracker> COPY location (vehicle_id, date, time, latitude, longitude) FROM '/home/vm1/Desktop/Files/Chapter 8/coordinates.csv' WITH header = true AND delimiter = '|';
cqlsh:vehicle_tracker> SELECT * FROM location LIMIT 3;

vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ bin/cassandra-cli
Connected to: "Test Cluster" on 127.0.0.1/9160
Welcome to Cassandra CLI version 2.0.7

The CLI is deprecated and will be removed in Cassandra 3.0.  Consider migrating to cqlsh.
CQL is fully backwards compatible with Thrift data; see http://www.datastax.com/dev/blog/thrift-to-cql3

Type 'help;' or '?' for help.
Type 'quit;' or 'exit;' to quit.

[default@unknown] USE vehicle_tracker;
Authenticated to keyspace: vehicle_tracker
[default@vehicle_tracker] LIST location;
Using default limit of 100
Using default cell limit of 100
-------------------
RowKey: ME100AAS:2014-05-19
=> (name=2014-05-19 08\:50\:00-0700:, value=, timestamp=1461481705332000)
=> (name=2014-05-19 08\:50\:00-0700:latitude, value=40465fecb31c219f, timestamp=1461481705332000)
=> (name=2014-05-19 08\:50\:00-0700:longitude, value=c050d00b8cfbfc65, timestamp=1461481705332000)
=> (name=2014-05-19 08\:40\:00-0700:, value=, timestamp=1461481705318000)
=> (name=2014-05-19 08\:40\:00-0700:latitude, value=40465f8cd20afa2f, timestamp=1461481705318000)
=> (name=2014-05-19 08\:40\:00-0700:longitude, value=c050d0ec8d5c7475, timestamp=1461481705318000)
=> (name=2014-05-19 08\:30\:00-0700:, value=, timestamp=1461481705301000)
=> (name=2014-05-19 08\:30\:00-0700:latitude, value=40465f0cfe154435, timestamp=1461481705301000)
=> (name=2014-05-19 08\:30\:00-0700:longitude, value=c050d5ef0f16f438, timestamp=1461481705301000)
=> (name=2014-05-19 08\:20\:00-0700:, value=, timestamp=1461481705287000)
=> (name=2014-05-19 08\:20\:00-0700:latitude, value=40465d2d77318fc5, timestamp=1461481705287000)
=> (name=2014-05-19 08\:20\:00-0700:longitude, value=c050d9b68cef672c, timestamp=1461481705287000)
=> (name=2014-05-19 08\:10\:00-0700:, value=, timestamp=1461481705281000)
=> (name=2014-05-19 08\:10\:00-0700:latitude, value=4046598e1932d6ed, timestamp=1461481705281000)
=> (name=2014-05-19 08\:10\:00-0700:longitude, value=c050de1b8ed1bf7b, timestamp=1461481705281000)
=> (name=2014-05-19 08\:00\:00-0700:, value=, timestamp=1461481705272000)
=> (name=2014-05-19 08\:00\:00-0700:latitude, value=40464f3e78e1932d, timestamp=1461481705272000)
=> (name=2014-05-19 08\:00\:00-0700:longitude, value=c050f62839042d8c, timestamp=1461481705272000)
-------------------
RowKey: FLN78197:2014-05-19
=> (name=2014-05-19 08\:50\:00-0700:, value=, timestamp=1461481705333000)
=> (name=2014-05-19 08\:50\:00-0700:latitude, value=4038ab96073de1e3, timestamp=1461481705333000)
=> (name=2014-05-19 08\:50\:00-0700:longitude, value=c05456ce9a2c6690, timestamp=1461481705333000)
=> (name=2014-05-19 08\:40\:00-0700:, value=, timestamp=1461481705320000)
=> (name=2014-05-19 08\:40\:00-0700:latitude, value=4038a5d5c31593e6, timestamp=1461481705320000)
=> (name=2014-05-19 08\:40\:00-0700:longitude, value=c05454f61aa3f035, timestamp=1461481705320000)
=> (name=2014-05-19 08\:30\:00-0700:, value=, timestamp=1461481705302000)
=> (name=2014-05-19 08\:30\:00-0700:latitude, value=4038a907b784662c, timestamp=1461481705302000)
=> (name=2014-05-19 08\:30\:00-0700:longitude, value=c054518897e99631, timestamp=1461481705302000)
=> (name=2014-05-19 08\:20\:00-0700:, value=, timestamp=1461481705297000)
=> (name=2014-05-19 08\:20\:00-0700:latitude, value=4038b1043e5321e6, timestamp=1461481705297000)
=> (name=2014-05-19 08\:20\:00-0700:longitude, value=c0544c2c16df3f96, timestamp=1461481705297000)
=> (name=2014-05-19 08\:10\:00-0700:, value=, timestamp=1461481705283000)
=> (name=2014-05-19 08\:10\:00-0700:latitude, value=4038b4d972cd7cf6, timestamp=1461481705283000)
=> (name=2014-05-19 08\:10\:00-0700:longitude, value=c0544821187e7c07, timestamp=1461481705283000)
=> (name=2014-05-19 08\:00\:00-0700:, value=, timestamp=1461481705275000)
=> (name=2014-05-19 08\:00\:00-0700:latitude, value=4038b3e425aee632, timestamp=1461481705275000)
=> (name=2014-05-19 08\:00\:00-0700:longitude, value=c054492f1a9fbe77, timestamp=1461481705275000)
-------------------
RowKey: LAKRM489:2014-05-19
=> (name=2014-05-19 08\:50\:00-0700:, value=, timestamp=1461481705328000)
=> (name=2014-05-19 08\:50\:00-0700:latitude, value=403e2057e23f24d9, timestamp=1461481705328000)
=> (name=2014-05-19 08\:50\:00-0700:longitude, value=c0576cf217093101, timestamp=1461481705328000)
=> (name=2014-05-19 08\:40\:00-0700:, value=, timestamp=1461481705304000)
=> (name=2014-05-19 08\:40\:00-0700:latitude, value=403e21dd1a21ea36, timestamp=1461481705304000)
=> (name=2014-05-19 08\:40\:00-0700:longitude, value=c0576bfa97e132b5, timestamp=1461481705304000)
=> (name=2014-05-19 08\:30\:00-0700:, value=, timestamp=1461481705299000)
=> (name=2014-05-19 08\:30\:00-0700:latitude, value=403e23b01c92ddbe, timestamp=1461481705299000)
=> (name=2014-05-19 08\:30\:00-0700:longitude, value=c0576a5f41aef6f9, timestamp=1461481705299000)
=> (name=2014-05-19 08\:20\:00-0700:, value=, timestamp=1461481705284000)
=> (name=2014-05-19 08\:20\:00-0700:latitude, value=403e26e147ae147b, timestamp=1461481705284000)
=> (name=2014-05-19 08\:20\:00-0700:longitude, value=c05768ba16e7a312, timestamp=1461481705284000)
=> (name=2014-05-19 08\:10\:00-0700:, value=, timestamp=1461481705279000)
=> (name=2014-05-19 08\:10\:00-0700:latitude, value=403e2b22af577100, timestamp=1461481705279000)
=> (name=2014-05-19 08\:10\:00-0700:longitude, value=c057661715c63ae2, timestamp=1461481705279000)
=> (name=2014-05-19 08\:00\:00-0700:, value=, timestamp=1461481705270000)
=> (name=2014-05-19 08\:00\:00-0700:latitude, value=403e2fff8a8f3a9b, timestamp=1461481705270000)
=> (name=2014-05-19 08\:00\:00-0700:longitude, value=c057626616b54e2b, timestamp=1461481705270000)
-------------------
RowKey: WA063JXD:2014-05-19
=> (name=2014-05-19 08\:50\:00-0700:, value=, timestamp=1461481705330000)
=> (name=2014-05-19 08\:50\:00-0700:latitude, value=4047d9c8a7a41e58, timestamp=1461481705330000)
=> (name=2014-05-19 08\:50\:00-0700:longitude, value=c05d4125785f8d2e, timestamp=1461481705330000)
=> (name=2014-05-19 08\:40\:00-0700:, value=, timestamp=1461481705314000)
=> (name=2014-05-19 08\:40\:00-0700:latitude, value=4047d912f4cf4a56, timestamp=1461481705314000)
=> (name=2014-05-19 08\:40\:00-0700:longitude, value=c05d42a3f8982cb2, timestamp=1461481705314000)
=> (name=2014-05-19 08\:30\:00-0700:, value=, timestamp=1461481705300000)
=> (name=2014-05-19 08\:30\:00-0700:latitude, value=4047d7f3300de4c5, timestamp=1461481705300000)
=> (name=2014-05-19 08\:30\:00-0700:longitude, value=c05d447c7820a30e, timestamp=1461481705300000)
=> (name=2014-05-19 08\:20\:00-0700:, value=, timestamp=1461481705285000)
=> (name=2014-05-19 08\:20\:00-0700:latitude, value=4047d70ff9724745, timestamp=1461481705285000)
=> (name=2014-05-19 08\:20\:00-0700:longitude, value=c05d45b777d0f1f5, timestamp=1461481705285000)
=> (name=2014-05-19 08\:10\:00-0700:, value=, timestamp=1461481705280000)
=> (name=2014-05-19 08\:10\:00-0700:latitude, value=4047d5e0f7fcfc40, timestamp=1461481705280000)
=> (name=2014-05-19 08\:10\:00-0700:longitude, value=c05d46fdb8fde2ef, timestamp=1461481705280000)
=> (name=2014-05-19 08\:00\:00-0700:, value=, timestamp=1461481705271000)
=> (name=2014-05-19 08\:00\:00-0700:latitude, value=4047d675d56f32be, timestamp=1461481705271000)
=> (name=2014-05-19 08\:00\:00-0700:longitude, value=c05d4f1db0142f62, timestamp=1461481705271000)
-------------------
RowKey: CA6AFL218:2014-05-19
=> (name=2014-05-19 08\:50\:00-0700:, value=, timestamp=1461481705321000)
=> (name=2014-05-19 08\:50\:00-0700:latitude, value=40420f4ed2cbea4f, timestamp=1461481705321000)
=> (name=2014-05-19 08\:50\:00-0700:longitude, value=c05ccb0b9dc2f406, timestamp=1461481705321000)
=> (name=2014-05-19 08\:40\:00-0700:, value=, timestamp=1461481705303000)
=> (name=2014-05-19 08\:40\:00-0700:latitude, value=404205a93af74cd3, timestamp=1461481705303000)
=> (name=2014-05-19 08\:40\:00-0700:longitude, value=c05ccb97891e2153, timestamp=1461481705303000)
=> (name=2014-05-19 08\:30\:00-0700:, value=, timestamp=1461481705298000)
=> (name=2014-05-19 08\:30\:00-0700:latitude, value=4041f4aceaaf35e3, timestamp=1461481705298000)
=> (name=2014-05-19 08\:30\:00-0700:longitude, value=c05ccd34373f316e, timestamp=1461481705298000)
=> (name=2014-05-19 08\:20\:00-0700:, value=, timestamp=1461481705283001)
=> (name=2014-05-19 08\:20\:00-0700:latitude, value=4041f10ecb74ddf8, timestamp=1461481705283001)
=> (name=2014-05-19 08\:20\:00-0700:longitude, value=c05cce6b00ffda40, timestamp=1461481705283001)
=> (name=2014-05-19 08\:10\:00-0700:, value=, timestamp=1461481705278000)
=> (name=2014-05-19 08\:10\:00-0700:latitude, value=4041e1aa2e3c536d, timestamp=1461481705278000)
=> (name=2014-05-19 08\:10\:00-0700:longitude, value=c05cd572ffd1dcd7, timestamp=1461481705278000)
=> (name=2014-05-19 08\:00\:00-0700:, value=, timestamp=1461481705269000)
=> (name=2014-05-19 08\:00\:00-0700:latitude, value=4041d8882f0e0a85, timestamp=1461481705269000)
=> (name=2014-05-19 08\:00\:00-0700:longitude, value=c05cd78effe2a3cf, timestamp=1461481705269000)

5 Rows Returned.
Elapsed time: 198 msec(s).

```
