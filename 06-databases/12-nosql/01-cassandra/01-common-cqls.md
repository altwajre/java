# common cqls

> Database - KEYSPACES

DESCRIBE KEYSPACES;

CREATE KEYSPACE testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

DESCRIBE KEYSPACE testdb;

DROP KEYSPACE testdb;

> Tables

USE testdb;

CREATE TABLE IF NOT EXISTS Customer (
customer_id text PRIMARY KEY,
name text,
age smallint
);

DESCRIBE TABLE Customer;

DROP TABLE IF EXISTS Customer;

> Remove all rows

TRUNCATE Customer;

> Insert

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

> Select

SELECT * FROM Customer;
