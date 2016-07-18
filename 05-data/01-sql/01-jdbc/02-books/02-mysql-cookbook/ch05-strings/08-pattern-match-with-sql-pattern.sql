
DROP TABLE IF EXISTS metal;
CREATE TABLE metal (name VARCHAR(20));

INSERT INTO metal (name) VALUES ('gold');
INSERT INTO metal (name) VALUES ('iron');
INSERT INTO metal (name) VALUES ('lead');
INSERT INTO metal (name) VALUES ('mercury');
INSERT INTO metal (name) VALUES ('platinum');
INSERT INTO metal (name) VALUES ('tin');

SELECT name FROM metal WHERE name LIKE 'me%';
/*
Strings that begin with a particular substring
+---------+
| name    |
+---------+
| mercury |
+---------+
1 row in set (0.00 sec)
*/

SELECT name FROM metal WHERE name LIKE '%d';
/*
Strings that end with a particular substring
+------+
| name |
+------+
| gold |
| lead |
+------+
2 rows in set (0.00 sec)
*/

SELECT name FROM metal WHERE name LIKE '%in%';
/*
Strings that contain a particular substring at any position
+----------+
| name     |
+----------+
| platinum |
| tin      |
+----------+
2 rows in set (0.00 sec)
*/

SELECT name FROM metal where name LIKE '__at%';
/*
Strings that contain a substring at a specific position (pattern matches if at the third position)
+----------+
| name     |
+----------+
| platinum |
+----------+
1 row in set (0.00 sec)
*/

SELECT name FROM metal WHERE name NOT LIKE '%i%';
/*
+---------+
| name    |
+---------+
| gold    |
| lead    |
| mercury |
+---------+
3 rows in set (0.00 sec)
*/

SELECT NULL LIKE '%', NULL NOT LIKE '%';
/*
SQL patterns do not match NULL values. This is true both for LIKE and for NOT LIKE
+---------------+-------------------+
| NULL LIKE '%' | NULL NOT LIKE '%' |
+---------------+-------------------+
|          NULL |              NULL |
+---------------+-------------------+
1 row in set (0.00 sec)
*/

