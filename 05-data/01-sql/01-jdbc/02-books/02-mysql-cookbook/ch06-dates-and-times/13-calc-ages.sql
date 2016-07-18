
DROP TABLE IF EXISTS sibling;
CREATE TABLE sibling
(
  name CHAR(20),
  birth DATE
);

INSERT INTO sibling (name,birth) VALUES('Gretchen','1942-04-14');
INSERT INTO sibling (name,birth) VALUES('Wilbur','1946-11-28');
INSERT INTO sibling (name,birth) VALUES('Franz','1953-03-05');

SELECT * FROM sibling;
/*
+----------+------------+
| name     | birth      |
+----------+------------+
| Gretchen | 1942-04-14 |
| Wilbur   | 1946-11-28 |
| Franz    | 1953-03-05 |
+----------+------------+
3 rows in set (0.00 sec)
*/

SELECT name, birth, CURDATE() AS today, TIMESTAMPDIFF(YEAR,birth,CURDATE()) AS 'age in years' FROM sibling;
/*
+----------+------------+------------+--------------+
| name     | birth      | today      | age in years |
+----------+------------+------------+--------------+
| Gretchen | 1942-04-14 | 2015-12-01 |           73 |
| Wilbur   | 1946-11-28 | 2015-12-01 |           69 |
| Franz    | 1953-03-05 | 2015-12-01 |           62 |
+----------+------------+------------+--------------+
3 rows in set (0.00 sec)
*/

SELECT name, birth, '1953-03-05' AS 'Franz'' birth', TIMESTAMPDIFF(YEAR,birth,'1953-03-05') AS 'age in years', TIMESTAMPDIFF(MONTH,birth,'1953-03-05') AS 'age in months' FROM sibling WHERE name <> 'Franz';
/*
+----------+------------+--------------+--------------+---------------+
| name     | birth      | Franz' birth | age in years | age in months |
+----------+------------+--------------+--------------+---------------+
| Gretchen | 1942-04-14 | 1953-03-05   |           10 |           130 |
| Wilbur   | 1946-11-28 | 1953-03-05   |            6 |            75 |
+----------+------------+--------------+--------------+---------------+
2 rows in set (0.00 sec)
*/

