
SELECT * FROM mail WHERE dstuser = 'tricia' ORDER BY srchost, srcuser;
/*
+---------------------+---------+---------+---------+---------+--------+
| t                   | srcuser | srchost | dstuser | dsthost | size   |
+---------------------+---------+---------+---------+---------+--------+
| 2014-05-15 10:25:52 | gene    | mars    | tricia  | saturn  | 998532 |
| 2014-05-14 11:52:17 | phil    | mars    | tricia  | saturn  |   5781 |
| 2014-05-19 12:49:23 | phil    | mars    | tricia  | saturn  |    873 |
| 2014-05-11 10:15:08 | barb    | saturn  | tricia  | mars    |  58274 |
| 2014-05-12 18:59:18 | barb    | saturn  | tricia  | venus   |    271 |
+---------------------+---------+---------+---------+---------+--------+
5 rows in set (0.00 sec)
*/

SELECT * FROM mail WHERE size > 50000 ORDER BY size DESC;
/*
+---------------------+---------+---------+---------+---------+---------+
| t                   | srcuser | srchost | dstuser | dsthost | size    |
+---------------------+---------+---------+---------+---------+---------+
| 2014-05-14 17:03:01 | tricia  | saturn  | phil    | venus   | 2394482 |
| 2014-05-15 10:25:52 | gene    | mars    | tricia  | saturn  |  998532 |
| 2014-05-12 12:48:13 | tricia  | mars    | gene    | venus   |  194925 |
| 2014-05-14 14:42:21 | barb    | venus   | barb    | venus   |   98151 |
| 2014-05-11 10:15:08 | barb    | saturn  | tricia  | mars    |   58274 |
+---------------------+---------+---------+---------+---------+---------+
5 rows in set (0.00 sec)
*/
