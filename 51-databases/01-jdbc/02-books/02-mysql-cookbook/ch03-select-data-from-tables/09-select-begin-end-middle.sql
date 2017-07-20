
SELECT * FROM profile LIMIT 1;
/*
+----+-------+------------+-------+----------------------+------+
| id | name  | birth      | color | foods                | cats |
+----+-------+------------+-------+----------------------+------+
|  1 | Sybil | 1970-04-13 | black | lutefisk,fadge,pizza |    4 |
+----+-------+------------+-------+----------------------+------+
1 row in set (0.00 sec)
*/

SELECT * FROM profile LIMIT 3;
/*
+----+-------+------------+-------+-----------------------+------+
| id | name  | birth      | color | foods                 | cats |
+----+-------+------------+-------+-----------------------+------+
|  1 | Sybil | 1970-04-13 | black | lutefisk,fadge,pizza  |    4 |
|  2 | Nancy | 1969-09-30 | white | burrito,curry,eggroll |    3 |
|  3 | Ralph | 1973-11-02 | red   | eggroll,pizza         |    4 |
+----+-------+------------+-------+-----------------------+------+
3 rows in set (0.00 sec)
*/

SELECT * FROM profile ORDER BY birth LIMIT 1;
/*
+----+--------+------------+-------+----------------+------+
| id | name   | birth      | color | foods          | cats |
+----+--------+------------+-------+----------------+------+
|  7 | Joanna | 1952-08-20 | green | lutefisk,fadge |    0 |
+----+--------+------------+-------+----------------+------+
1 row in set (0.00 sec)
*/

SELECT * FROM profile ORDER BY birth DESC LIMIT 1;
/*
+----+-------+------------+-------+---------------+------+
| id | name  | birth      | color | foods         | cats |
+----+-------+------------+-------+---------------+------+
|  3 | Ralph | 1973-11-02 | red   | eggroll,pizza |    4 |
+----+-------+------------+-------+---------------+------+
1 row in set (0.00 sec)
*/

SELECT name, DATE_FORMAT(birth,'%m-%d') AS birthday FROM profile ORDER BY birthday LIMIT 1;
/*
+-------+----------+
| name  | birthday |
+-------+----------+
| Henry | 02-14    |
+-------+----------+
1 row in set (0.00 sec)
*/

SELECT * FROM profile ORDER BY birth LIMIT 2,1;
/*
To pull rows from the middle of a result set, use the two-argument form of LIMIT.
The arguments indicate how many rows to skip and how many to return.
+----+---------+------------+-------+---------------+------+
| id | name    | birth      | color | foods         | cats |
+----+---------+------------+-------+---------------+------+
|  4 | Lothair | 1963-07-04 | blue  | burrito,curry |    5 |
+----+---------+------------+-------+---------------+------+
1 row in set (0.00 sec)
*/

SELECT COUNT(*) FROM profile;
/*
+----------+
| COUNT(*) |
+----------+
|        8 |
+----------+
1 row in set (0.00 sec)
*/