
DROP TABLE IF EXISTS t;
CREATE TABLE t (d DATE);

INSERT INTO t (d) VALUES(STR_TO_DATE('May 13, 2007', '%M %d, %Y'));
SELECT d FROM t;
/*
Convert string to date
+------------+
| d          |
+------------+
| 2007-05-13 |
+------------+
1 row in set (0.00 sec)
*/

SELECT d, DATE_FORMAT(d,'%M %d, %Y') FROM date_val;
/*
+------------+----------------------------+
| d          | DATE_FORMAT(d,'%M %d, %Y') |
+------------+----------------------------+
| 1864-02-28 | February 28, 1864          |
| 1900-01-15 | January 15, 1900           |
| 1999-12-31 | December 31, 1999          |
| 2000-06-04 | June 04, 2000              |
| 2017-03-16 | March 16, 2017             |
+------------+----------------------------+
5 rows in set (0.00 sec)
*/

SELECT d, DATE_FORMAT(d, '%M %d, %Y') as date FROM date_val;
/*
+------------+-------------------+
| d          | date              |
+------------+-------------------+
| 1864-02-28 | February 28, 1864 |
| 1900-01-15 | January 15, 1900  |
| 1999-12-31 | December 31, 1999 |
| 2000-06-04 | June 04, 2000     |
| 2017-03-16 | March 16, 2017    |
+------------+-------------------+
5 rows in set (0.00 sec)
*/

SELECT dt, DATE_FORMAT(dt,'%c/%e/%y %r') AS format1, DATE_FORMAT(dt,'%M %e, %Y %T') AS format2 FROM datetime_val;
/*
%c Month of year (1..12)
%e Day of month (1..31)
%y Two-digit year
%r 12-hour time with AM or PM suffix
%M Complete month name
%Y Four-digit year
%T 24-hour time
+---------------------+----------------------+----------------------------+
| dt                  | format1              | format2                    |
+---------------------+----------------------+----------------------------+
| 1970-01-01 00:00:00 | 1/1/70 12:00:00 AM   | January 1, 1970 00:00:00   |
| 1999-12-31 09:00:00 | 12/31/99 09:00:00 AM | December 31, 1999 09:00:00 |
| 2000-06-04 15:45:30 | 6/4/00 03:45:30 PM   | June 4, 2000 15:45:30      |
| 2017-03-16 12:30:15 | 3/16/17 12:30:15 PM  | March 16, 2017 12:30:15    |
+---------------------+----------------------+----------------------------+
4 rows in set (0.00 sec)
*/

SELECT dt, TIME_FORMAT(dt,'%r') AS '12-hour time', TIME_FORMAT(dt, '%T') AS '24-hour time' FROM datetime_val;
/*
%r 12-hour time with AM or PM suffix
%T 24-hour time
+---------------------+--------------+--------------+
| dt                  | 12-hour time | 24-hour time |
+---------------------+--------------+--------------+
| 1970-01-01 00:00:00 | 12:00:00 AM  | 00:00:00     |
| 1999-12-31 09:00:00 | 09:00:00 AM  | 09:00:00     |
| 2000-06-04 15:45:30 | 03:45:30 PM  | 15:45:30     |
| 2017-03-16 12:30:15 | 12:30:15 PM  | 12:30:15     |
+---------------------+--------------+--------------+
4 rows in set (0.00 sec)
*/

CREATE FUNCTION time_ampm(t TIME)
RETURNS VARCHAR(13) # mm:dd:ss {a.m.|p.m.} format
DETERMINISTIC
RETURN CONCAT(LEFT(TIME_FORMAT(t, '%r'),9),IF(TIME_TO_SEC(t) < 12*60*60,'a.m.','p.m.'));

SELECT t1, time_ampm(t1) FROM time_val;
/*
+----------+---------------+
| t1       | time_ampm(t1) |
+----------+---------------+
| 15:00:00 | 03:00:00 p.m. |
| 05:01:30 | 05:01:30 a.m. |
| 12:30:20 | 12:30:20 p.m. |
+----------+---------------+
3 rows in set (0.00 sec)
*/
