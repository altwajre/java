
SELECT * FROM mail;
/*
+---------------------+---------+---------+---------+---------+---------+
| t                   | srcuser | srchost | dstuser | dsthost | size    |
+---------------------+---------+---------+---------+---------+---------+
| 2014-05-11 10:15:08 | barb    | saturn  | tricia  | mars    |   58274 |
| 2014-05-12 12:48:13 | tricia  | mars    | gene    | venus   |  194925 |
| 2014-05-12 15:02:49 | phil    | mars    | phil    | saturn  |    1048 |
| 2014-05-12 18:59:18 | barb    | saturn  | tricia  | venus   |     271 |
| 2014-05-14 09:31:37 | gene    | venus   | barb    | mars    |    2291 |
| 2014-05-14 11:52:17 | phil    | mars    | tricia  | saturn  |    5781 |
| 2014-05-14 14:42:21 | barb    | venus   | barb    | venus   |   98151 |
| 2014-05-14 17:03:01 | tricia  | saturn  | phil    | venus   | 2394482 |
| 2014-05-15 07:17:48 | gene    | mars    | gene    | saturn  |    3824 |
| 2014-05-15 08:50:57 | phil    | venus   | phil    | venus   |     978 |
| 2014-05-15 10:25:52 | gene    | mars    | tricia  | saturn  |  998532 |
| 2014-05-15 17:35:31 | gene    | saturn  | gene    | mars    |    3856 |
| 2014-05-16 09:00:28 | gene    | venus   | barb    | mars    |     613 |
| 2014-05-16 23:04:19 | phil    | venus   | barb    | venus   |   10294 |
| 2014-05-19 12:49:23 | phil    | mars    | tricia  | saturn  |     873 |
| 2014-05-19 22:21:51 | gene    | saturn  | gene    | venus   |   23992 |
+---------------------+---------+---------+---------+---------+---------+
16 rows in set (0.00 sec)
*/

SELECT srcuser, srchost, t, size FROM mail;
/*
+---------+---------+---------------------+---------+
| srcuser | srchost | t                   | size    |
+---------+---------+---------------------+---------+
| barb    | saturn  | 2014-05-11 10:15:08 |   58274 |
| tricia  | mars    | 2014-05-12 12:48:13 |  194925 |
| phil    | mars    | 2014-05-12 15:02:49 |    1048 |
| barb    | saturn  | 2014-05-12 18:59:18 |     271 |
| gene    | venus   | 2014-05-14 09:31:37 |    2291 |
| phil    | mars    | 2014-05-14 11:52:17 |    5781 |
| barb    | venus   | 2014-05-14 14:42:21 |   98151 |
| tricia  | saturn  | 2014-05-14 17:03:01 | 2394482 |
| gene    | mars    | 2014-05-15 07:17:48 |    3824 |
| phil    | venus   | 2014-05-15 08:50:57 |     978 |
| gene    | mars    | 2014-05-15 10:25:52 |  998532 |
| gene    | saturn  | 2014-05-15 17:35:31 |    3856 |
| gene    | venus   | 2014-05-16 09:00:28 |     613 |
| phil    | venus   | 2014-05-16 23:04:19 |   10294 |
| phil    | mars    | 2014-05-19 12:49:23 |     873 |
| gene    | saturn  | 2014-05-19 22:21:51 |   23992 |
+---------+---------+---------------------+---------+
16 rows in set (0.00 sec)
*/

SELECT t, srcuser, srchost FROM mail WHERE srchost = 'venus';
/*
+---------------------+---------+---------+
| t                   | srcuser | srchost |
+---------------------+---------+---------+
| 2014-05-14 09:31:37 | gene    | venus   |
| 2014-05-14 14:42:21 | barb    | venus   |
| 2014-05-15 08:50:57 | phil    | venus   |
| 2014-05-16 09:00:28 | gene    | venus   |
| 2014-05-16 23:04:19 | phil    | venus   |
+---------------------+---------+---------+
5 rows in set (0.00 sec)
*/

SELECT t, srcuser, srchost FROM mail WHERE srchost LIKE 's%';
/*
% acts as a wildcard that matches any string.
+---------------------+---------+---------+
| t                   | srcuser | srchost |
+---------------------+---------+---------+
| 2014-05-11 10:15:08 | barb    | saturn  |
| 2014-05-12 18:59:18 | barb    | saturn  |
| 2014-05-14 17:03:01 | tricia  | saturn  |
| 2014-05-15 17:35:31 | gene    | saturn  |
| 2014-05-19 22:21:51 | gene    | saturn  |
+---------------------+---------+---------+
5 rows in set (0.00 sec)
*/

SELECT * FROM mail WHERE srcuser = 'barb' AND dstuser = 'tricia';
/*
+---------------------+---------+---------+---------+---------+-------+
| t                   | srcuser | srchost | dstuser | dsthost | size  |
+---------------------+---------+---------+---------+---------+-------+
| 2014-05-11 10:15:08 | barb    | saturn  | tricia  | mars    | 58274 |
| 2014-05-12 18:59:18 | barb    | saturn  | tricia  | venus   |   271 |
+---------------------+---------+---------+---------+---------+-------+
2 rows in set (0.00 sec)
*/

SELECT t, CONCAT(srcuser,'@',srchost), size FROM mail;
/*
+---------------------+-----------------------------+---------+
| t                   | CONCAT(srcuser,'@',srchost) | size    |
+---------------------+-----------------------------+---------+
| 2014-05-11 10:15:08 | barb@saturn                 |   58274 |
| 2014-05-12 12:48:13 | tricia@mars                 |  194925 |
| 2014-05-12 15:02:49 | phil@mars                   |    1048 |
| 2014-05-12 18:59:18 | barb@saturn                 |     271 |
| 2014-05-14 09:31:37 | gene@venus                  |    2291 |
| 2014-05-14 11:52:17 | phil@mars                   |    5781 |
| 2014-05-14 14:42:21 | barb@venus                  |   98151 |
| 2014-05-14 17:03:01 | tricia@saturn               | 2394482 |
| 2014-05-15 07:17:48 | gene@mars                   |    3824 |
| 2014-05-15 08:50:57 | phil@venus                  |     978 |
| 2014-05-15 10:25:52 | gene@mars                   |  998532 |
| 2014-05-15 17:35:31 | gene@saturn                 |    3856 |
| 2014-05-16 09:00:28 | gene@venus                  |     613 |
| 2014-05-16 23:04:19 | phil@venus                  |   10294 |
| 2014-05-19 12:49:23 | phil@mars                   |     873 |
| 2014-05-19 22:21:51 | gene@saturn                 |   23992 |
+---------------------+-----------------------------+---------+
16 rows in set (0.00 sec)
*/