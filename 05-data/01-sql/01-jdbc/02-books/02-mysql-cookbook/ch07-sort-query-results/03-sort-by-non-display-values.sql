
SELECT t, srcuser, CONCAT(FLOOR((size+1023)/1024),'K') AS size_in_K FROM mail WHERE size > 50000 ORDER BY size_in_K;
/*
problem: they sort lexically, not numerically.
+---------------------+---------+-----------+
| t                   | srcuser | size_in_K |
+---------------------+---------+-----------+
| 2014-05-12 12:48:13 | tricia  | 191K      |
| 2014-05-14 17:03:01 | tricia  | 2339K     |
| 2014-05-11 10:15:08 | barb    | 57K       |
| 2014-05-14 14:42:21 | barb    | 96K       |
| 2014-05-15 10:25:52 | gene    | 976K      |
+---------------------+---------+-----------+
5 rows in set (0.00 sec)
*/

SELECT t, srcuser, CONCAT(FLOOR((size+1023)/1024),'K') AS size_in_K FROM mail WHERE size > 50000 ORDER BY size;
/*
To achieve the desired output order, display the string, but use actual numeric size for sorting.
+---------------------+---------+-----------+
| t                   | srcuser | size_in_K |
+---------------------+---------+-----------+
| 2014-05-11 10:15:08 | barb    | 57K       |
| 2014-05-14 14:42:21 | barb    | 96K       |
| 2014-05-12 12:48:13 | tricia  | 191K      |
| 2014-05-15 10:25:52 | gene    | 976K      |
| 2014-05-14 17:03:01 | tricia  | 2339K     |
+---------------------+---------+-----------+
5 rows in set (0.00 sec)
*/

DROP TABLE IF EXISTS roster;
CREATE TABLE roster
(
  name        CHAR(30), # player name
  jersey_num  CHAR(3)   # jersey number
);

INSERT INTO roster (name, jersey_num)
  VALUES
    ('Lynne','29'),
    ('Ella','0'),
    ('Elizabeth','100'),
    ('Nancy','00'),
    ('Jean','8'),
    ('Sherry','47')
;

SELECT name, jersey_num FROM roster;
/*
+-----------+------------+
| name      | jersey_num |
+-----------+------------+
| Lynne     | 29         |
| Ella      | 0          |
| Elizabeth | 100        |
| Nancy     | 00         |
| Jean      | 8          |
| Sherry    | 47         |
+-----------+------------+
6 rows in set (0.00 sec)
*/

SELECT name, jersey_num FROM roster ORDER BY jersey_num;
/*
+-----------+------------+
| name      | jersey_num |
+-----------+------------+
| Ella      | 0          |
| Nancy     | 00         |
| Elizabeth | 100        |
| Lynne     | 29         |
| Sherry    | 47         |
| Jean      | 8          |
+-----------+------------+
6 rows in set (0.00 sec)
*/

SELECT name, jersey_num FROM roster ORDER BY jersey_num+0;
/*
add zero to force a string-to-number conversion
+-----------+------------+
| name      | jersey_num |
+-----------+------------+
| Ella      | 0          |
| Nancy     | 00         |
| Jean      | 8          |
| Lynne     | 29         |
| Sherry    | 47         |
| Elizabeth | 100        |
+-----------+------------+
6 rows in set (0.00 sec)
*/

SELECT t, CONCAT(srcuser,'@',srchost) AS sender, size FROM mail WHERE size > 50000 ORDER BY srchost, srcuser;
/*
if you want to treat the hostname as more significant than the username. sort
+---------------------+---------------+---------+
| t                   | sender        | size    |
+---------------------+---------------+---------+
| 2014-05-15 10:25:52 | gene@mars     |  998532 |
| 2014-05-12 12:48:13 | tricia@mars   |  194925 |
| 2014-05-11 10:15:08 | barb@saturn   |   58274 |
| 2014-05-14 17:03:01 | tricia@saturn | 2394482 |
| 2014-05-14 14:42:21 | barb@venus    |   98151 |
+---------------------+---------------+---------+
5 rows in set (0.00 sec)
*/

DROP TABLE IF EXISTS name;
#@ _CREATE_TABLE_
CREATE TABLE name
(
  last_name   CHAR(20),
  first_name  CHAR(20)
);
#@ _CREATE_TABLE_

INSERT INTO name (first_name,last_name) VALUES('Kevin','Brown');
INSERT INTO name (first_name,last_name) VALUES('Vida','Blue');
INSERT INTO name (first_name,last_name) VALUES('Pete','Gray');
INSERT INTO name (first_name,last_name) VALUES('Devon','White');
INSERT INTO name (first_name,last_name) VALUES('Rondell','White');

SELECT * FROM name;

SELECT last_name, first_name FROM name ORDER BY last_name, first_name;
/*
sort last and then first
+-----------+------------+
| last_name | first_name |
+-----------+------------+
| Blue      | Vida       |
| Brown     | Kevin      |
| Gray      | Pete       |
| White     | Devon      |
| White     | Rondell    |
+-----------+------------+
5 rows in set (0.00 sec)
*/

SELECT CONCAT(first_name,' ',last_name) AS full_name FROM name ORDER BY last_name, first_name;
/*
+---------------+
| full_name     |
+---------------+
| Vida Blue     |
| Kevin Brown   |
| Pete Gray     |
| Devon White   |
| Rondell White |
+---------------+
5 rows in set (0.00 sec)
*/

