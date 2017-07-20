//$ cd /Users/whan/cassandra/apache-cassandra-3.9
//$ bin/cassandra -f <- start cassandra in foreground
//ctrl+c to shutdown
//$ bin/cqlsh
//cqlsh> SOURCE '~/mydir/file.cql' <- executes cql statements

DROP KEYSPACE IF EXISTS webapi;

CREATE KEYSPACE webapi WITH replication = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};

USE webapi;

DROP TABLE IF EXISTS contact;

CREATE TABLE contact (
	id uuid,
	name text,
	PRIMARY KEY (id)
);

BEGIN BATCH
  INSERT INTO contact (id, name) VALUES (uuid(), 'Tom');
  INSERT INTO contact (id, name) VALUES (uuid(), 'Dick');
  INSERT INTO contact (id, name) VALUES (uuid(), 'Harry');
APPLY BATCH;

SELECT id, name FROM webapi.contact;

DROP TABLE IF EXISTS car;

CREATE TABLE car (
	id int,
	make text,
	color text,
	PRIMARY KEY (id)
);

BEGIN BATCH
  INSERT INTO car (id, make, color) VALUES (1, 'Honda', 'red');
  INSERT INTO car (id, make, color) VALUES (2, 'BMW', 'yellow');
  INSERT INTO car (id, make, color) VALUES (3, 'Ford', 'green');
APPLY BATCH;

SELECT id, make, color FROM webapi.car;

UPDATE car SET color = 'blue' WHERE id = 1;

SELECT id, make, color FROM webapi.car;

DELETE FROM car WHERE id = 3;

SELECT id, make, color FROM webapi.car;

SELECT id, make, color FROM webapi.car WHERE id = 2;
