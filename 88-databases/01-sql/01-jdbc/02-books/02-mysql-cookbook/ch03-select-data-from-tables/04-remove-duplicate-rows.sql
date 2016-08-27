SELECT srcuser FROM mail;
/*
+---------+
| srcuser |
+---------+
| barb    |
| tricia  |
| phil    |
| barb    |
| gene    |
| phil    |
| barb    |
| tricia  |
| gene    |
| phil    |
| gene    |
| gene    |
| gene    |
| phil    |
| phil    |
| gene    |
+---------+
16 rows in set (0.00 sec)
*/

SELECT DISTINCT srcuser FROM mail;
/*
+---------+
| srcuser |
+---------+
| barb    |
| tricia  |
| phil    |
| gene    |
+---------+
4 rows in set (0.00 sec)
*/

SELECT COUNT(DISTINCT srcuser) FROM mail;
/*
+-------------------------+
| COUNT(DISTINCT srcuser) |
+-------------------------+
|                       4 |
+-------------------------+
1 row in set (0.00 sec)
*/

SELECT DISTINCT YEAR(t), MONTH(t), DAYOFMONTH(t) FROM mail;
/*
+---------+----------+---------------+
| YEAR(t) | MONTH(t) | DAYOFMONTH(t) |
+---------+----------+---------------+
|    2014 |        5 |            11 |
|    2014 |        5 |            12 |
|    2014 |        5 |            14 |
|    2014 |        5 |            15 |
|    2014 |        5 |            16 |
|    2014 |        5 |            19 |
+---------+----------+---------------+
6 rows in set (0.00 sec)
*/