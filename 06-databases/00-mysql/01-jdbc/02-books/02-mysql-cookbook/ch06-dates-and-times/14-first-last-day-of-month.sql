
SELECT d, DATE_SUB(d,INTERVAL DAYOFMONTH(d)-1 DAY) AS '1st of month' FROM date_val;
/*
subtracts (day of month - 1) to get the first day of the month
+------------+--------------+
| d          | 1st of month |
+------------+--------------+
| 1864-02-28 | 1864-02-01   |
| 1900-01-15 | 1900-01-01   |
| 1999-12-31 | 1999-12-01   |
| 2000-06-04 | 2000-06-01   |
| 2017-03-16 | 2017-03-01   |
+------------+--------------+
5 rows in set (0.00 sec)
*/

SELECT d, DATE_ADD(DATE_SUB(d,INTERVAL DAYOFMONTH(d)-1 DAY),INTERVAL -1 MONTH) AS '1st of previous month',
DATE_ADD(DATE_SUB(d,INTERVAL DAYOFMONTH(d)-1 DAY),INTERVAL 1 MONTH) AS '1st of following month' FROM date_val;
/*
+------------+-----------------------+------------------------+
| d          | 1st of previous month | 1st of following month |
+------------+-----------------------+------------------------+
| 1864-02-28 | 1864-01-01            | 1864-03-01             |
| 1900-01-15 | 1899-12-01            | 1900-02-01             |
| 1999-12-31 | 1999-11-01            | 2000-01-01             |
| 2000-06-04 | 2000-05-01            | 2000-07-01             |
| 2017-03-16 | 2017-02-01            | 2017-04-01             |
+------------+-----------------------+------------------------+
5 rows in set (0.00 sec)
*/

SELECT d, LAST_DAY(d) AS 'last of month' FROM date_val;
/*
+------------+---------------+
| d          | last of month |
+------------+---------------+
| 1864-02-28 | 1864-02-29    |
| 1900-01-15 | 1900-01-31    |
| 1999-12-31 | 1999-12-31    |
| 2000-06-04 | 2000-06-30    |
| 2017-03-16 | 2017-03-31    |
+------------+---------------+
5 rows in set (0.00 sec)
*/

SELECT d, LAST_DAY(DATE_ADD(d,INTERVAL -1 MONTH)) AS 'last of previous month',
LAST_DAY(DATE_ADD(d,INTERVAL 1 MONTH)) AS 'last of following month' FROM date_val;
/*
find the last day of the previous and following months
+------------+------------------------+-------------------------+
| d          | last of previous month | last of following month |
+------------+------------------------+-------------------------+
| 1864-02-28 | 1864-01-31             | 1864-03-31              |
| 1900-01-15 | 1899-12-31             | 1900-02-28              |
| 1999-12-31 | 1999-11-30             | 2000-01-31              |
| 2000-06-04 | 2000-05-31             | 2000-07-31              |
| 2017-03-16 | 2017-02-28             | 2017-04-30              |
+------------+------------------------+-------------------------+
5 rows in set (0.00 sec)
*/

SELECT d, DAYOFMONTH(LAST_DAY(d)) AS 'days in month' FROM date_val;
/*
+------------+---------------+
| d          | days in month |
+------------+---------------+
| 1864-02-28 |            29 |
| 1900-01-15 |            31 |
| 1999-12-31 |            31 |
| 2000-06-04 |            30 |
| 2017-03-16 |            31 |
+------------+---------------+
5 rows in set (0.00 sec)
*/


