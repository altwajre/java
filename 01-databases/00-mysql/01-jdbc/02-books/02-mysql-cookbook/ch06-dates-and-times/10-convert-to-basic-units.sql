
SELECT t1, TIME_TO_SEC(t1) AS 'TIME to seconds', SEC_TO_TIME(TIME_TO_SEC(t1)) AS 'TIME to seconds to TIME' FROM time_val;
/*
+----------+-----------------+-------------------------+
| t1       | TIME to seconds | TIME to seconds to TIME |
+----------+-----------------+-------------------------+
| 15:00:00 |           54000 | 15:00:00                |
| 05:01:30 |           18090 | 05:01:30                |
| 12:30:20 |           45020 | 12:30:20                |
+----------+-----------------+-------------------------+
3 rows in set (0.00 sec)
*/

SELECT t1, TIME_TO_SEC(t1) AS 'seconds', TIME_TO_SEC(t1)/60 AS 'minutes', TIME_TO_SEC(t1)/(60*60) AS 'hours' FROM time_val;
/*
+----------+---------+----------+---------+
| t1       | seconds | minutes  | hours   |
+----------+---------+----------+---------+
| 15:00:00 |   54000 | 900.0000 | 15.0000 |
| 05:01:30 |   18090 | 301.5000 |  5.0250 |
| 12:30:20 |   45020 | 750.3333 | 12.5056 |
+----------+---------+----------+---------+
3 rows in set (0.00 sec)
*/

SELECT dt, TIME_TO_SEC(dt) AS 'time part in seconds', SEC_TO_TIME(TIME_TO_SEC(dt)) AS 'time part as TIME' FROM datetime_val;
/*
+---------------------+----------------------+-------------------+
| dt                  | time part in seconds | time part as TIME |
+---------------------+----------------------+-------------------+
| 1970-01-01 00:00:00 |                    0 | 00:00:00          |
| 1999-12-31 09:00:00 |                32400 | 09:00:00          |
| 2000-06-04 15:45:30 |                56730 | 15:45:30          |
| 2017-03-16 12:30:15 |                45015 | 12:30:15          |
+---------------------+----------------------+-------------------+
4 rows in set (0.00 sec)
*/

SELECT d, TO_DAYS(d) AS 'DATE to days', FROM_DAYS(TO_DAYS(d)) AS 'DATE to days to DATE' FROM date_val;
/*
+------------+--------------+----------------------+
| d          | DATE to days | DATE to days to DATE |
+------------+--------------+----------------------+
| 1864-02-28 |       680870 | 1864-02-28           |
| 1900-01-15 |       693975 | 1900-01-15           |
| 1999-12-31 |       730484 | 1999-12-31           |
| 2000-06-04 |       730640 | 2000-06-04           |
| 2017-03-16 |       736769 | 2017-03-16           |
+------------+--------------+----------------------+
5 rows in set (0.00 sec)
*/
