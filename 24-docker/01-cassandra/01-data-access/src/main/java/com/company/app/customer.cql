DROP KEYSPACE IF EXISTS testdb;
CREATE KEYSPACE IF NOT EXISTS testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE testdb;

DROP TABLE IF EXISTS customer;

CREATE TABLE IF NOT EXISTS customer (
customer_id text PRIMARY KEY,
name text,
age text
);

INSERT INTO customer (customer_id, name, age)
VALUES ('123', 'Tom', '28');

SELECT * FROM customer;
