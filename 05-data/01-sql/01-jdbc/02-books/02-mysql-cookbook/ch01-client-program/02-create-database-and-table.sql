/*
% mysql -h localhost -u cbuser -p
Enter password: cbpass

mysql> SHOW DATABASES;
mysql> SELECT DATABASE();  <-- determine current database
mysql> DROP DATABASE cookbook;
mysql> SHOW TABLES;

*/

CREATE DATABASE cookbook;
SELECT DATABASE();

USE cookbook;
DROP TABLE IF EXISTS limbs;
CREATE TABLE limbs (thing VARCHAR(20), legs INT, arms INT);

INSERT INTO limbs (thing,legs,arms) VALUES('human',2,2);
INSERT INTO limbs (thing,legs,arms) VALUES('insect',6,0);
INSERT INTO limbs (thing,legs,arms) VALUES('squid',0,10);
INSERT INTO limbs (thing,legs,arms) VALUES('fish',0,0);
INSERT INTO limbs (thing,legs,arms) VALUES('centipede',100,0);
INSERT INTO limbs (thing,legs,arms) VALUES('table',4,0);
INSERT INTO limbs (thing,legs,arms) VALUES('armchair',4,2);
INSERT INTO limbs (thing,legs,arms) VALUES('phonograph',0,1);
INSERT INTO limbs (thing,legs,arms) VALUES('tripod',3,0);
INSERT INTO limbs (thing,legs,arms) VALUES('Peg Leg Pete',1,2);
INSERT INTO limbs (thing,legs,arms) VALUES('space alien',NULL,NULL);

SELECT * FROM limbs;