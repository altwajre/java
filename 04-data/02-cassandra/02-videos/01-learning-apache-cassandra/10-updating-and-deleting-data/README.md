# Updating And Deleting Data

Finished Deleting Data section.

## Updating Data

```
vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ bin/cqlsh
Connected to Test Cluster at localhost:9160.
[cqlsh 4.1.1 | Cassandra 2.0.7 | CQL spec 3.1.1 | Thrift protocol 19.39.0]
Use HELP for help.
cqlsh> USE home_security ;
cqlsh:home_security> SELECT home_id, address, phone, alt_phone, contact_name FROM home WHERE home_id = 'H01474777';

 home_id   | address             | phone        | alt_phone | contact_name
-----------+---------------------+--------------+-----------+--------------
 H01474777 | 518 Crestview Drive | 310-775-4011 |      null | Jed Clampett

(1 rows)
cqlsh:home_security> UPDATE home SET phone = '310-883-7179' WHERE home_id = 'H01474777';
cqlsh:home_security> SELECT home_id, address, phone, alt_phone, contact_name FROM home WHERE home_id = 'H01474777';

 home_id   | address             | phone        | alt_phone | contact_name
-----------+---------------------+--------------+-----------+--------------
 H01474777 | 518 Crestview Drive | 310-883-7179 |      null | Jed Clampett

(1 rows)
```

## Understanding How Updating Works

```
vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ bin/nodetool flush home_security home;
vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ cd /var/lib/cassandra/data/home_security/home/
vm1@ubuntu:/var/lib/cassandra/data/home_security/home$ ls
home_security-home-jb-1-CompressionInfo.db  home_security-home-jb-1-Summary.db          home_security-home-jb-2-Index.db
home_security-home-jb-1-Data.db             home_security-home-jb-1-TOC.txt             home_security-home-jb-2-Statistics.db
home_security-home-jb-1-Filter.db           home_security-home-jb-2-CompressionInfo.db  home_security-home-jb-2-Summary.db
home_security-home-jb-1-Index.db            home_security-home-jb-2-Data.db             home_security-home-jb-2-TOC.txt
home_security-home-jb-1-Statistics.db       home_security-home-jb-2-Filter.db
vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ bin/sstable2json /var/lib/cassandra/data/home_security/home/home_security-home-jb-2-Data.db
[
{"key": "483031343734373737","columns": [["phone","310-883-7179",1461539572634000]]}
]
```

## Deleting Data

```
vm1@ubuntu:~/cassandra/apache-cassandra-2.0.7$ bin/cqlsh
Connected to Test Cluster at localhost:9160.
[cqlsh 4.1.1 | Cassandra 2.0.7 | CQL spec 3.1.1 | Thrift protocol 19.39.0]
Use HELP for help.
cqlsh> CREATE KEYSPACE playground WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};
cqlsh> USE playground ;
cqlsh:playground> SOURCE '/home/vm1/Desktop/Files/Chapter 10/create.cql';
6 rows imported in 0.059 seconds.
cqlsh:playground> SELECT * FROM messages_by_user;

 sender | sent                     | body                                   | message_id                           | recip
--------+--------------------------+----------------------------------------+--------------------------------------+--------
   axel | 2013-07-21 15:34:55-0700 |          for sure! 6:00 at our spot :) | e74fb030-bffc-11e3-8a33-0800200c9a66 | jonesy
  bobby | 2013-07-21 15:34:01-0700 |                            np, will do | dd963780-bffc-11e3-8a33-0800200c9a66 |   juju
  annie | 2013-07-21 15:31:23-0700 | will be great to see you guys tonight! | f594a470-bffc-11e3-8a33-0800200c9a66 |   juju
 jonesy | 2013-07-21 15:34:03-0700 |                         meet up today? | c764e974-bffc-11e3-8a33-0800200c9a66 |   axel
   juju | 2013-07-21 15:32:58-0700 |                             and mixer! | c764e971-bffc-11e3-8a33-0800200c9a66 |  bobby
   juju | 2013-07-21 15:32:16-0700 |                  please pick up snacks | f81d4fae-7dec-11d0-a765-00a0c91e6bf6 |  bobby

(6 rows)

cqlsh:playground> DELETE body FROM messages_by_user WHERE sender='juju' AND sent='2013-07-21 15:32:16'; <- delete column value of a row
cqlsh:playground> SELECT * FROM messages_by_user;

 sender | sent                     | body                                   | message_id                           | recip
--------+--------------------------+----------------------------------------+--------------------------------------+--------
   axel | 2013-07-21 15:34:55-0700 |          for sure! 6:00 at our spot :) | e74fb030-bffc-11e3-8a33-0800200c9a66 | jonesy
  bobby | 2013-07-21 15:34:01-0700 |                            np, will do | dd963780-bffc-11e3-8a33-0800200c9a66 |   juju
  annie | 2013-07-21 15:31:23-0700 | will be great to see you guys tonight! | f594a470-bffc-11e3-8a33-0800200c9a66 |   juju
 jonesy | 2013-07-21 15:34:03-0700 |                         meet up today? | c764e974-bffc-11e3-8a33-0800200c9a66 |   axel
   juju | 2013-07-21 15:32:58-0700 |                             and mixer! | c764e971-bffc-11e3-8a33-0800200c9a66 |  bobby
   juju | 2013-07-21 15:32:16-0700 |                                   null | f81d4fae-7dec-11d0-a765-00a0c91e6bf6 |  bobby

(6 rows)

cqlsh:playground> DELETE FROM messages_by_user WHERE sender='juju' AND sent='2013-07-21 15:32:16'; <- delete a row
cqlsh:playground> SELECT * FROM messages_by_user;

 sender | sent                     | body                                   | message_id                           | recip
--------+--------------------------+----------------------------------------+--------------------------------------+--------
   axel | 2013-07-21 15:34:55-0700 |          for sure! 6:00 at our spot :) | e74fb030-bffc-11e3-8a33-0800200c9a66 | jonesy
  bobby | 2013-07-21 15:34:01-0700 |                            np, will do | dd963780-bffc-11e3-8a33-0800200c9a66 |   juju
  annie | 2013-07-21 15:31:23-0700 | will be great to see you guys tonight! | f594a470-bffc-11e3-8a33-0800200c9a66 |   juju
 jonesy | 2013-07-21 15:34:03-0700 |                         meet up today? | c764e974-bffc-11e3-8a33-0800200c9a66 |   axel
   juju | 2013-07-21 15:32:58-0700 |                             and mixer! | c764e971-bffc-11e3-8a33-0800200c9a66 |  bobby

(5 rows)

cqlsh:playground> TRUNCATE messages_by_user; <- delete all rows
cqlsh:playground> SELECT * FROM messages_by_user;

(0 rows)

cqlsh:playground> DROP TABLE messages_by_user;
cqlsh:playground> SELECT * FROM messages_by_user;
Bad Request: unconfigured columnfamily messages_by_user
cqlsh:playground> DROP KEYSPACE playground; <- drop database
cqlsh:playground> DESC KEYSPACES;

system  vehicle_tracker  home_security  system_traces
```