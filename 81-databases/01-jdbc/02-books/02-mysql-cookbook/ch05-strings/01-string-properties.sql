
SHOW CHARACTER SET;
/*
+----------+---------------------------------+---------------------+--------+
| Charset  | Description                     | Default collation   | Maxlen |
+----------+---------------------------------+---------------------+--------+
| big5     | Big5 Traditional Chinese        | big5_chinese_ci     |      2 |
| dec8     | DEC West European               | dec8_swedish_ci     |      1 |
| cp850    | DOS West European               | cp850_general_ci    |      1 |
...
| utf8     | UTF-8 Unicode                   | utf8_general_ci     |      3 |
| ucs2     | UCS-2 Unicode                   | ucs2_general_ci     |      2 |
...
| cp932    | SJIS for Windows Japanese       | cp932_japanese_ci   |      2 |
| eucjpms  | UJIS for Windows Japanese       | eucjpms_japanese_ci |      3 |
| gb18030  | China National Standard GB18030 | gb18030_chinese_ci  |      4 |
+----------+---------------------------------+---------------------+--------+
41 rows in set (0.00 sec)
*/

SET @s = CONVERT('abc' USING utf8);
SELECT LENGTH(@s), CHAR_LENGTH(@s);
/*
utf8 contains only single-byte characters.
+------------+-----------------+
| LENGTH(@s) | CHAR_LENGTH(@s) |
+------------+-----------------+
|          3 |               3 |
+------------+-----------------+
1 row in set (0.00 sec)
*/

SET @s = CONVERT('abc' USING ucs2);
SELECT LENGTH(@s), CHAR_LENGTH(@s);
/*
ucs2 characters are encoded using two bytes.
+------------+-----------------+
| LENGTH(@s) | CHAR_LENGTH(@s) |
+------------+-----------------+
|          6 |               3 |
+------------+-----------------+
1 row in set (0.00 sec)
*/

SHOW COLLATION LIKE 'latin1%';
/*
+-------------------+---------+----+---------+----------+---------+
| Collation         | Charset | Id | Default | Compiled | Sortlen |
+-------------------+---------+----+---------+----------+---------+
| latin1_german1_ci | latin1  |  5 |         | Yes      |       1 |
| latin1_swedish_ci | latin1  |  8 | Yes     | Yes      |       1 |
| latin1_danish_ci  | latin1  | 15 |         | Yes      |       1 |
| latin1_german2_ci | latin1  | 31 |         | Yes      |       2 |
| latin1_bin        | latin1  | 47 |         | Yes      |       1 |
| latin1_general_ci | latin1  | 48 |         | Yes      |       1 |
| latin1_general_cs | latin1  | 49 |         | Yes      |       1 |
| latin1_spanish_ci | latin1  | 94 |         | Yes      |       1 |
+-------------------+---------+----+---------+----------+---------+
8 rows in set (0.00 sec)
*/

CREATE TABLE t (c CHAR(3) CHARACTER SET latin1);
INSERT INTO t (c) VALUES('AAA'),('bbb'),('aaa'),('BBB');
SELECT c FROM t;
/*
+------+
| c    |
+------+
| AAA  |
| bbb  |
| aaa  |
| BBB  |
+------+
4 rows in set (0.00 sec)
*/

SELECT c FROM t ORDER BY c COLLATE latin1_danish_ci;
/*
case-insensitive collation sorting
+------+
| c    |
+------+
| AAA  |
| aaa  |
| bbb  |
| BBB  |
+------+
4 rows in set (0.00 sec)
*/

SELECT c FROM t ORDER BY c COLLATE latin1_general_cs;
/*
case-sensitive collation sorting
+------+
| c    |
+------+
| AAA  |
| aaa  |
| BBB  |
| bbb  |
+------+
4 rows in set (0.00 sec)
*/

SELECT c FROM t ORDER BY c COLLATE latin1_bin;
/*
a binary collation sorting
+------+
| c    |
+------+
| AAA  |
| BBB  |
| aaa  |
| bbb  |
+------+
4 rows in set (0.00 sec)
*/

DROP TABLE IF EXISTS t;
CREATE TABLE t (c CHAR(2) CHARACTER SET utf8);
INSERT INTO t (c) VALUES('cg'),('ch'),('ci'),('lk'),('ll'),('lm');
SELECT c FROM t ORDER BY c COLLATE utf8_general_ci;
/*
utf8_general_ci sorting
+------+
| c    |
+------+
| cg   |
| ch   |
| ci   |
| lk   |
| ll   |
| lm   |
+------+
6 rows in set (0.00 sec)
*/

SELECT c FROM t ORDER BY c COLLATE utf8_spanish2_ci;
/*
utf8_spanish2_ci sorting
+------+
| c    |
+------+
| cg   |
| ci   |
| ch   |
| lk   |
| lm   |
| ll   |
+------+
6 rows in set (0.00 sec)
*/
