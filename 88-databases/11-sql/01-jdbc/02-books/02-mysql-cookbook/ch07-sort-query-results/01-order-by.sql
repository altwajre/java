
DROP TABLE IF EXISTS driver_log;
CREATE TABLE driver_log
(
  rec_id    INT UNSIGNED NOT NULL AUTO_INCREMENT,
  name      VARCHAR(20) NOT NULL,
  trav_date DATE NOT NULL,
  miles     INT NOT NULL,
  PRIMARY KEY  (rec_id)
);

INSERT INTO driver_log (name,trav_date,miles)
  VALUES
    ('Ben','2014-07-30',152),
    ('Suzi','2014-07-29',391),
    ('Henry','2014-07-29',300),
    ('Henry','2014-07-27',96),
    ('Ben','2014-07-29',131),
    ('Henry','2014-07-26',115),
    ('Suzi','2014-08-02',502),
    ('Henry','2014-08-01',197),
    ('Ben','2014-08-02',79),
    ('Henry','2014-07-30',203)
;

SELECT * FROM driver_log ORDER BY name;
/*
+--------+-------+------------+-------+
| rec_id | name  | trav_date  | miles |
+--------+-------+------------+-------+
|      1 | Ben   | 2014-07-30 |   152 |
|      5 | Ben   | 2014-07-29 |   131 |
|      9 | Ben   | 2014-08-02 |    79 |
|      3 | Henry | 2014-07-29 |   300 |
|      4 | Henry | 2014-07-27 |    96 |
|      6 | Henry | 2014-07-26 |   115 |
|      8 | Henry | 2014-08-01 |   197 |
|     10 | Henry | 2014-07-30 |   203 |
|      2 | Suzi  | 2014-07-29 |   391 |
|      7 | Suzi  | 2014-08-02 |   502 |
+--------+-------+------------+-------+
10 rows in set (0.00 sec)
*/

SELECT * FROM driver_log ORDER BY name DESC;
/*
+--------+-------+------------+-------+
| rec_id | name  | trav_date  | miles |
+--------+-------+------------+-------+
|      2 | Suzi  | 2014-07-29 |   391 |
|      7 | Suzi  | 2014-08-02 |   502 |
|      3 | Henry | 2014-07-29 |   300 |
|      4 | Henry | 2014-07-27 |    96 |
|      6 | Henry | 2014-07-26 |   115 |
|      8 | Henry | 2014-08-01 |   197 |
|     10 | Henry | 2014-07-30 |   203 |
|      1 | Ben   | 2014-07-30 |   152 |
|      5 | Ben   | 2014-07-29 |   131 |
|      9 | Ben   | 2014-08-02 |    79 |
+--------+-------+------------+-------+
10 rows in set (0.00 sec)
*/

SELECT * FROM driver_log ORDER BY name, trav_date;
/*
+--------+-------+------------+-------+
| rec_id | name  | trav_date  | miles |
+--------+-------+------------+-------+
|      5 | Ben   | 2014-07-29 |   131 |
|      1 | Ben   | 2014-07-30 |   152 |
|      9 | Ben   | 2014-08-02 |    79 |
|      6 | Henry | 2014-07-26 |   115 |
|      4 | Henry | 2014-07-27 |    96 |
|      3 | Henry | 2014-07-29 |   300 |
|     10 | Henry | 2014-07-30 |   203 |
|      8 | Henry | 2014-08-01 |   197 |
|      2 | Suzi  | 2014-07-29 |   391 |
|      7 | Suzi  | 2014-08-02 |   502 |
+--------+-------+------------+-------+
10 rows in set (0.00 sec)
*/

SELECT * FROM driver_log ORDER BY name DESC, trav_date;
/*
+--------+-------+------------+-------+
| rec_id | name  | trav_date  | miles |
+--------+-------+------------+-------+
|      2 | Suzi  | 2014-07-29 |   391 |
|      7 | Suzi  | 2014-08-02 |   502 |
|      6 | Henry | 2014-07-26 |   115 |
|      4 | Henry | 2014-07-27 |    96 |
|      3 | Henry | 2014-07-29 |   300 |
|     10 | Henry | 2014-07-30 |   203 |
|      8 | Henry | 2014-08-01 |   197 |
|      5 | Ben   | 2014-07-29 |   131 |
|      1 | Ben   | 2014-07-30 |   152 |
|      9 | Ben   | 2014-08-02 |    79 |
+--------+-------+------------+-------+
10 rows in set (0.00 sec)
*/


SELECT name, trav_date, miles, AS distance FROM driver_log ORDER BY distance;

SELECT name, trav_date, DAYNAME(trav_date) FROM driver_log ORDER BY name, trav_date;
/*
+-------+------------+--------------------+
| name  | trav_date  | DAYNAME(trav_date) |
+-------+------------+--------------------+
| Ben   | 2014-07-29 | Tuesday            |
| Ben   | 2014-07-30 | Wednesday          |
| Ben   | 2014-08-02 | Saturday           |
| Henry | 2014-07-26 | Saturday           |
| Henry | 2014-07-27 | Sunday             |
| Henry | 2014-07-29 | Tuesday            |
| Henry | 2014-07-30 | Wednesday          |
| Henry | 2014-08-01 | Friday             |
| Suzi  | 2014-07-29 | Tuesday            |
| Suzi  | 2014-08-02 | Saturday           |
+-------+------------+--------------------+
10 rows in set (0.00 sec)
*/

SELECT name, trav_date, miles AS distance FROM driver_log ORDER BY distance;
/*
+-------+------------+----------+
| name  | trav_date  | distance |
+-------+------------+----------+
| Ben   | 2014-08-02 |       79 |
| Henry | 2014-07-27 |       96 |
| Henry | 2014-07-26 |      115 |
| Ben   | 2014-07-29 |      131 |
| Ben   | 2014-07-30 |      152 |
| Henry | 2014-08-01 |      197 |
| Henry | 2014-07-30 |      203 |
| Henry | 2014-07-29 |      300 |
| Suzi  | 2014-07-29 |      391 |
| Suzi  | 2014-08-02 |      502 |
+-------+------------+----------+
10 rows in set (0.00 sec)
*/


