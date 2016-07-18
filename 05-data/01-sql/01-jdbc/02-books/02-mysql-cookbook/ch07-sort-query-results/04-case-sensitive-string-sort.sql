
DROP TABLE IF EXISTS str_val;
CREATE TABLE str_val
(
  ci_str  CHAR(3) CHARACTER SET latin1 COLLATE latin1_swedish_ci,
  cs_str  CHAR(3) CHARACTER SET latin1 COLLATE latin1_general_cs,
  bin_str BINARY(3)
);

INSERT INTO str_val (ci_str,cs_str,bin_str) VALUES
('AAA','AAA','AAA'),
('aaa','aaa','aaa'),
('bbb','bbb','bbb'),
('BBB','BBB','BBB');

SELECT * FROM str_val;
/*
+--------+--------+---------+
| ci_str | cs_str | bin_str |
+--------+--------+---------+
| AAA    | AAA    | AAA     |
| aaa    | aaa    | aaa     |
| bbb    | bbb    | bbb     |
| BBB    | BBB    | BBB     |
+--------+--------+---------+
4 rows in set (0.00 sec)
*/

SELECT * FROM str_val ORDER BY ci_str;
/*
case-insensitive sort
+--------+--------+---------+
| ci_str | cs_str | bin_str |
+--------+--------+---------+
| AAA    | AAA    | AAA     |
| aaa    | aaa    | aaa     |
| bbb    | bbb    | bbb     |
| BBB    | BBB    | BBB     |
+--------+--------+---------+
4 rows in set (0.00 sec)
*/

SELECT * FROM str_val ORDER BY cs_str;
/*
case-sensitive sort
+--------+--------+---------+
| ci_str | cs_str | bin_str |
+--------+--------+---------+
| AAA    | AAA    | AAA     |
| aaa    | aaa    | aaa     |
| BBB    | BBB    | BBB     |
| bbb    | bbb    | bbb     |
+--------+--------+---------+
4 rows in set (0.00 sec)
*/

SELECT * FROM str_val ORDER BY bin_str;
/*
binary strings sort
+--------+--------+---------+
| ci_str | cs_str | bin_str |
+--------+--------+---------+
| AAA    | AAA    | AAA     |
| BBB    | BBB    | BBB     |
| aaa    | aaa    | aaa     |
| bbb    | bbb    | bbb     |
+--------+--------+---------+
4 rows in set (0.00 sec)
*/

SELECT ci_str FROM str_val ORDER BY ci_str COLLATE latin1_general_cs;
/*
sort case-insensitive strins in case-sensitive fashion, order the sorted values using a case-sensitive collation
+--------+
| ci_str |
+--------+
| AAA    |
| aaa    |
| BBB    |
| bbb    |
+--------+
4 rows in set (0.00 sec)
*/
