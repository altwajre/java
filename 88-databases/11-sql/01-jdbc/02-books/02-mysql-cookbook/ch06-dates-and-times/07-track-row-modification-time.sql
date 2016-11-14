# Using TIMESTAMP or DATETIME to Track Row Modification Times

DROP TABLE IF EXISTS tsdemo;
CREATE TABLE tsdemo
(
  val INT,
  ts_both TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  ts_create TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
/*
- ts_both auto-initializes and auto-updates. This is useful for tracking the time of any change to a row,
  for both inserts and updates.
- ts_create auto-initializes only. This is useful when you want a column to be set to the time at which a row is
  created, but remain constant thereafter.
*/

INSERT INTO tsdemo (val) VALUES(5);
INSERT INTO tsdemo (val,ts_both,ts_create) VALUES(10,NULL,NULL);
SELECT * FROM tsdemo;
/*
+------+---------------------+---------------------+
| val  | ts_both             | ts_create           |
+------+---------------------+---------------------+
|    5 | 2015-11-30 09:40:47 | 2015-11-30 09:40:47 |
|   10 | 2015-11-30 09:40:47 | 2015-11-30 09:40:47 |
+------+---------------------+---------------------+
2 rows in set (0.00 sec)
*/

UPDATE tsdemo SET val = 11 WHERE val = 10;
SELECT * FROM tsdemo;
/*
To see auto-updating in action, issue a statement that changes one row's va column and check its effect on the table's
contents. The result shows that the auto-update columns are updated.
+------+---------------------+---------------------+
| val  | ts_both             | ts_create           |
+------+---------------------+---------------------+
|    5 | 2015-11-30 09:40:47 | 2015-11-30 09:40:47 |
|   11 | 2015-11-30 10:24:01 | 2015-11-30 09:40:47 |
+------+---------------------+---------------------+
2 rows in set (0.00 sec)
*/

UPDATE tsdemo SET val = val + 1;
SELECT * FROM tsdemo;
/*
If you modify multiple rows, updates occur for the auto-update columns in each row
+------+---------------------+---------------------+
| val  | ts_both             | ts_create           |
+------+---------------------+---------------------+
|    6 | 2015-11-30 13:59:00 | 2015-11-30 09:40:47 |
|   12 | 2015-11-30 13:59:00 | 2015-11-30 09:40:47 |
+------+---------------------+---------------------+
2 rows in set (0.00 sec)
*/

UPDATE tsdemo SET val = val;
SELECT * FROM tsdemo;
/*
An UPDATE statement that doesn't actually change any value in a row doesn't modify auto-update columns.
+------+---------------------+---------------------+
| val  | ts_both             | ts_create           |
+------+---------------------+---------------------+
|    6 | 2015-11-30 13:59:00 | 2015-11-30 09:40:47 |
|   12 | 2015-11-30 13:59:00 | 2015-11-30 09:40:47 |
+------+---------------------+---------------------+
2 rows in set (0.00 sec)
*/

