
DROP TABLE IF EXISTS t;
CREATE TABLE t (c1 CHAR(10), c2 VARCHAR(10));
INSERT INTO t (c1,c2) VALUES('abc       ','abc       ');
SELECT c1, c2, CHAR_LENGTH(c1), CHAR_LENGTH(c2) FROM t;
/*
non-binary CHAR ends without spaces
non-binary VARCHAR ends with spaces
+------+------------+-----------------+-----------------+
| c1   | c2         | CHAR_LENGTH(c1) | CHAR_LENGTH(c2) |
+------+------------+-----------------+-----------------+
| abc  | abc        |               3 |              10 |
+------+------------+-----------------+-----------------+
1 row in set (0.00 sec)
*/