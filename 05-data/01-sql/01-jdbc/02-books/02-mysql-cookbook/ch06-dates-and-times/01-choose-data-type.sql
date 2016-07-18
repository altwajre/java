
# time_val.sql

DROP TABLE IF EXISTS time_val;
CREATE TABLE time_val
(
  t1  TIME,
  t2  TIME
);

INSERT INTO time_val (t1,t2) VALUES('15:00:00','15:00:00');
INSERT INTO time_val (t1,t2) VALUES('05:01:30','02:30:20');
INSERT INTO time_val (t1,t2) VALUES('12:30:20','17:30:45');

SELECT * FROM time_val;

SELECT t1, t2 FROM time_val;
/*
+----------+----------+
| t1       | t2       |
+----------+----------+
| 15:00:00 | 15:00:00 |
| 05:01:30 | 02:30:20 |
| 12:30:20 | 17:30:45 |
+----------+----------+
3 rows in set (0.00 sec)
*/

# date_val.sql

# table of test date_val

DROP TABLE IF EXISTS date_val;
CREATE TABLE date_val
(
  d DATE
);

# Do not change the 1900 or 2000 dates. They're needed to illustrate
# points about leap-year testing.

INSERT INTO date_val (d) VALUES('1864-02-28');
INSERT INTO date_val (d) VALUES('1900-01-15');
INSERT INTO date_val (d) VALUES('1999-12-31');
INSERT INTO date_val (d) VALUES('2000-06-04');
INSERT INTO date_val (d) VALUES('2017-03-16');

SELECT d FROM date_val;
/*
+------------+
| d          |
+------------+
| 1864-02-28 |
| 1900-01-15 |
| 1999-12-31 |
| 2000-06-04 |
| 2017-03-16 |
+------------+
5 rows in set (0.00 sec)
*/

# datetime_val.sql

# table of test date-and-time values (same values as timestamps table,
# but as DATETIME values)

DROP TABLE IF EXISTS datetime_val;
CREATE TABLE datetime_val
(
  dt  DATETIME
);

# These values are the same for datetime_val and timestamp_val.
# Don't change one without changing the other.

INSERT INTO datetime_val (dt) VALUES('1970-01-01 00:00:00');
INSERT INTO datetime_val (dt) VALUES('1999-12-31 09:00:00');
INSERT INTO datetime_val (dt) VALUES('2000-06-04 15:45:30');
INSERT INTO datetime_val (dt) VALUES('2017-03-16 12:30:15');

SELECT * FROM datetime_val;

SELECT dt FROM datetime_val;
/*
+---------------------+
| dt                  |
+---------------------+
| 1970-01-01 00:00:00 |
| 1999-12-31 09:00:00 |
| 2000-06-04 15:45:30 |
| 2017-03-16 12:30:15 |
+---------------------+
4 rows in set (0.00 sec)
*/