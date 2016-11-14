
SELECT name FROM metal WHERE name REGEXP '^me';
/*
Strings that begin with a particular substring
+---------+
| name    |
+---------+
| mercury |
+---------+
1 row in set (0.00 sec)
*/

SELECT name FROM metal WHERE name REGEXP 'd$';
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

SELECT name FROM metal WHERE name REGEXP 'in';
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

SELECT name FROM metal WHERE name REGEXP '^..at';
/*
+----------+
| name     |
+----------+
| platinum |
+----------+
1 row in set (0.00 sec)
*/

SELECT NULL REGEXP '.*', NULL NOT REGEXP '.*';
/*
Regular expressions do not match NULL values. This is true both for REGEXP and for NOT REGEXP
+------------------+----------------------+
| NULL REGEXP '.*' | NULL NOT REGEXP '.*' |
+------------------+----------------------+
|             NULL |                 NULL |
+------------------+----------------------+
1 row in set (0.00 sec)
*/