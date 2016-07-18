
DROP TABLE IF EXISTS mail2;

CREATE TABLE mail2 LIKE mail;

# saving a query result in a table
INSERT INTO mail2 SELECT * FROM mail WHERE srcuser = 'barb';

SELECT * FROM mail2;
/*
+---------------------+---------+---------+---------+---------+-------+
| t                   | srcuser | srchost | dstuser | dsthost | size  |
+---------------------+---------+---------+---------+---------+-------+
| 2014-05-11 10:15:08 | barb    | saturn  | tricia  | mars    | 58274 |
| 2014-05-12 18:59:18 | barb    | saturn  | tricia  | venus   |   271 |
| 2014-05-14 14:42:21 | barb    | venus   | barb    | venus   | 98151 |
+---------------------+---------+---------+---------+---------+-------+
3 rows in set (0.00 sec)
*/