
SELECT * FROM mail WHERE srcuser = 'phil';
/*
+---------------------+---------+---------+---------+---------+-------+
| t                   | srcuser | srchost | dstuser | dsthost | size  |
+---------------------+---------+---------+---------+---------+-------+
| 2014-05-12 15:02:49 | phil    | mars    | phil    | saturn  |  1048 |
| 2014-05-14 11:52:17 | phil    | mars    | tricia  | saturn  |  5781 |
| 2014-05-15 08:50:57 | phil    | venus   | phil    | venus   |   978 |
| 2014-05-16 23:04:19 | phil    | venus   | barb    | venus   | 10294 |
| 2014-05-19 12:49:23 | phil    | mars    | tricia  | saturn  |   873 |
+---------------------+---------+---------+---------+---------+-------+
5 rows in set (0.00 sec)
*/

SELECT * FROM mail WHERE srcuser = 'phil' ORDER BY t DESC;
/*
+---------------------+---------+---------+---------+---------+-------+
| t                   | srcuser | srchost | dstuser | dsthost | size  |
+---------------------+---------+---------+---------+---------+-------+
| 2014-05-19 12:49:23 | phil    | mars    | tricia  | saturn  |   873 |
| 2014-05-16 23:04:19 | phil    | venus   | barb    | venus   | 10294 |
| 2014-05-15 08:50:57 | phil    | venus   | phil    | venus   |   978 |
| 2014-05-14 11:52:17 | phil    | mars    | tricia  | saturn  |  5781 |
| 2014-05-12 15:02:49 | phil    | mars    | phil    | saturn  |  1048 |
+---------------------+---------+---------+---------+---------+-------+
5 rows in set (0.00 sec)
*/
