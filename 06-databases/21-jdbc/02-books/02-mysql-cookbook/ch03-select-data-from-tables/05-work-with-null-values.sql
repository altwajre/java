
SELECT * FROM expt WHERE score IS NULL;
/*
+---------+------+-------+
| subject | test | score |
+---------+------+-------+
| Jane    | C    |  NULL |
| Jane    | D    |  NULL |
| Marvin  | D    |  NULL |
+---------+------+-------+
3 rows in set (0.00 sec)
*/

SELECT * FROM expt WHERE score IS NOT NULL;
/*
+---------+------+-------+
| subject | test | score |
+---------+------+-------+
| Jane    | A    |    47 |
| Jane    | B    |    50 |
| Marvin  | A    |    52 |
| Marvin  | B    |    45 |
| Marvin  | C    |    53 |
+---------+------+-------+
5 rows in set (0.00 sec)
*/

SELECT NULL = NULL, NULL <=> NULL;
/*
+-------------+---------------+
| NULL = NULL | NULL <=> NULL |
+-------------+---------------+
|        NULL |             1 |
+-------------+---------------+
1 row in set (0.00 sec)
*/

SELECT subject, test, IF(score IS NULL, 'Unknown', score) AS 'score' FROM expt;
/*
+---------+------+---------+
| subject | test | score   |
+---------+------+---------+
| Jane    | A    | 47      |
| Jane    | B    | 50      |
| Jane    | C    | Unknown |
| Jane    | D    | Unknown |
| Marvin  | A    | 52      |
| Marvin  | B    | 45      |
| Marvin  | C    | 53      |
| Marvin  | D    | Unknown |
+---------+------+---------+
8 rows in set (0.00 sec)
*/
