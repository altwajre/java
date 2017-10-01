/*
$ mysql cookbook < 06-execute-sql-from-file.sql

read sql file from mysql session source = \.
mysql> source limbs.sql;
mysql> \. limbs.sql;

mysql> CREATE DATABASE temp;
mysql> GRANT ALL ON temp.* TO 'whan'@'localhost';  -- Grant user to cookbook database

copy a database to another database
$ mysqldump cookbook > dump.sql  <-- generates database backups by write a set of sql statements
mysql> create database temp;
mysql> GRANT ALL ON temp.* TO 'whan'@'localhost';
$ mysql temp < dump.sql

mysql takes output from other programs as its input
$ mysqldump cookbook | mysql -h other-host.example.com cookbook
*/

DROP TABLE IF EXISTS limbs;
CREATE TABLE limbs
(
 thing VARCHAR(20), -- what the thing is
 legs INT,          -- number of legs it has
 arms INT           -- number of arms it has
);

INSERT INTO limbs (thing,legs,arms) VALUES('human',2,2);
INSERT INTO limbs (thing,legs,arms) VALUES('insert',6,0);
INSERT INTO limbs (thing,legs,arms) VALUES('squid',0,10);
INSERT INTO limbs (thing,legs,arms) VALUES('fish',0,0);
INSERT INTO limbs (thing,legs,arms) VALUES('centipede',100,0);
INSERT INTO limbs (thing,legs,arms) VALUES('table',4,0);
INSERT INTO limbs (thing,legs,arms) VALUES('armchair',4,2);
INSERT INTO limbs (thing,legs,arms) VALUES('phonograph',0,1);
INSERT INTO limbs (thing,legs,arms) VALUES('tripod',3,0);
INSERT INTO limbs (thing,legs,arms) VALUES('Peg Leg Pete',1,2);
INSERT INTO limbs (thing,legs,arms) VALUES('space alien',NULL,NULL);

