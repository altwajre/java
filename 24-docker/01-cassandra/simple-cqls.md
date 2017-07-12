# common cqls

> Database - KEYSPACES

DESCRIBE KEYSPACES;

CREATE KEYSPACE testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

DESCRIBE KEYSPACE testdb;

DROP KEYSPACE testdb;

> Tables

USE testdb;

CREATE TABLE IF NOT EXISTS customer (
customer_id text PRIMARY KEY,
name text,
age smallint
);

DESCRIBE TABLE customer;

DROP TABLE IF EXISTS customer;

> Remove all rows

TRUNCATE customer;

> Insert

INSERT INTO customer (customer_id, name, age)
VALUES ('123', 'Tom', 28);

> Select

SELECT * FROM customer;
