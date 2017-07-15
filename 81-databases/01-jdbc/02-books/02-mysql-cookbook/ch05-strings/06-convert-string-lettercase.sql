
SELECT thing, UPPER(thing), LOWER(thing) FROM limbs;
/*
+--------------+--------------+--------------+
| thing        | UPPER(thing) | LOWER(thing) |
+--------------+--------------+--------------+
| human        | HUMAN        | human        |
| insert       | INSERT       | insert       |
| squid        | SQUID        | squid        |
| fish         | FISH         | fish         |
| centipede    | CENTIPEDE    | centipede    |
| table        | TABLE        | table        |
| armchair     | ARMCHAIR     | armchair     |
| phonograph   | PHONOGRAPH   | phonograph   |
| tripod       | TRIPOD       | tripod       |
| Peg Leg Pete | PEG LEG PETE | peg leg pete |
| space alien  | SPACE ALIEN  | space alien  |
+--------------+--------------+--------------+
11 rows in set (0.00 sec)
*/

DROP TABLE IF EXISTS t;
CREATE TABLE t (b BLOB) SELECT 'aBcD' AS b;
SELECT b, UPPER(b), LOWER(b) FROM t;
/*
Lettercase does not apply binary strings
+------+----------+----------+
| b    | UPPER(b) | LOWER(b) |
+------+----------+----------+
| aBcD | aBcD     | aBcD     |
+------+----------+----------+
1 row in set (0.00 sec)
*/

SELECT b, UPPER(CONVERT(b USING latin1)) AS upper, LOWER(CONVERT(b USING latin1)) AS lower FROM t;
/*
To map a binary string to a given lettercase, convert it to a nonbinary string.
+------+-------+-------+
| b    | upper | lower |
+------+-------+-------+
| aBcD | ABCD  | abcd  |
+------+-------+-------+
1 row in set (0.00 sec)
*/

CREATE FUNCTION initial_cap (s VARCHAR(255)) RETURNS VARCHAR(255) DETERMINISTIC RETURN CONCAT(UPPER(LEFT(s,1)),MID(s,2));
SELECT thing, initial_cap(thing) FROM limbs;
/*
+--------------+--------------------+
| thing        | initial_cap(thing) |
+--------------+--------------------+
| human        | Human              |
| insect       | Insect             |
| squid        | Squid              |
| fish         | Fish               |
| centipede    | Centipede          |
| table        | Table              |
| armchair     | Armchair           |
| phonograph   | Phonograph         |
| tripod       | Tripod             |
| Peg Leg Pete | Peg Leg Pete       |
| space alien  | Space alien        |
+--------------+--------------------+
11 rows in set (0.00 sec)
*/

