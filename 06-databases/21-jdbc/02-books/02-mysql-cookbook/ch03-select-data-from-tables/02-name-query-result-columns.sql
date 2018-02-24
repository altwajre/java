
SELECT t, srcuser, srchost, size FROM mail;
/*
+---------------------+---------+---------+---------+
| t                   | srcuser | srchost | size    |
+---------------------+---------+---------+---------+
| 2014-05-11 10:15:08 | barb    | saturn  |   58274 |
| 2014-05-12 12:48:13 | tricia  | mars    |  194925 |
| 2014-05-12 15:02:49 | phil    | mars    |    1048 |
| 2014-05-12 18:59:18 | barb    | saturn  |     271 |
| 2014-05-14 09:31:37 | gene    | venus   |    2291 |
| 2014-05-14 11:52:17 | phil    | mars    |    5781 |
| 2014-05-14 14:42:21 | barb    | venus   |   98151 |
| 2014-05-14 17:03:01 | tricia  | saturn  | 2394482 |
| 2014-05-15 07:17:48 | gene    | mars    |    3824 |
| 2014-05-15 08:50:57 | phil    | venus   |     978 |
| 2014-05-15 10:25:52 | gene    | mars    |  998532 |
| 2014-05-15 17:35:31 | gene    | saturn  |    3856 |
| 2014-05-16 09:00:28 | gene    | venus   |     613 |
| 2014-05-16 23:04:19 | phil    | venus   |   10294 |
| 2014-05-19 12:49:23 | phil    | mars    |     873 |
| 2014-05-19 22:21:51 | gene    | saturn  |   23992 |
+---------------------+---------+---------+---------+
16 rows in set (0.00 sec)
*/

SELECT DATE_FORMAT(t,'%M %e, %Y'), CONCAT(srcuser,'@',srchost), size FROM mail;
/*
+----------------------------+-----------------------------+---------+
| DATE_FORMAT(t,'%M %e, %Y') | CONCAT(srcuser,'@',srchost) | size    |
+----------------------------+-----------------------------+---------+
| May 11, 2014               | barb@saturn                 |   58274 |
| May 12, 2014               | tricia@mars                 |  194925 |
| May 12, 2014               | phil@mars                   |    1048 |
| May 12, 2014               | barb@saturn                 |     271 |
| May 14, 2014               | gene@venus                  |    2291 |
| May 14, 2014               | phil@mars                   |    5781 |
| May 14, 2014               | barb@venus                  |   98151 |
| May 14, 2014               | tricia@saturn               | 2394482 |
| May 15, 2014               | gene@mars                   |    3824 |
| May 15, 2014               | phil@venus                  |     978 |
| May 15, 2014               | gene@mars                   |  998532 |
| May 15, 2014               | gene@saturn                 |    3856 |
| May 16, 2014               | gene@venus                  |     613 |
| May 16, 2014               | phil@venus                  |   10294 |
| May 19, 2014               | phil@mars                   |     873 |
| May 19, 2014               | gene@saturn                 |   23992 |
+----------------------------+-----------------------------+---------+
16 rows in set (0.00 sec)
*/

SELECT DATE_FORMAT(t,'%M %e, %Y') AS date_sent, CONCAT(srcuser,'@',srchost) AS sender, size FROM mail;
/*
+--------------+---------------+---------+
| date_sent    | sender        | size    |
+--------------+---------------+---------+
| May 11, 2014 | barb@saturn   |   58274 |
| May 12, 2014 | tricia@mars   |  194925 |
| May 12, 2014 | phil@mars     |    1048 |
| May 12, 2014 | barb@saturn   |     271 |
| May 14, 2014 | gene@venus    |    2291 |
| May 14, 2014 | phil@mars     |    5781 |
| May 14, 2014 | barb@venus    |   98151 |
| May 14, 2014 | tricia@saturn | 2394482 |
| May 15, 2014 | gene@mars     |    3824 |
| May 15, 2014 | phil@venus    |     978 |
| May 15, 2014 | gene@mars     |  998532 |
| May 15, 2014 | gene@saturn   |    3856 |
| May 16, 2014 | gene@venus    |     613 |
| May 16, 2014 | phil@venus    |   10294 |
| May 19, 2014 | phil@mars     |     873 |
| May 19, 2014 | gene@saturn   |   23992 |
+--------------+---------------+---------+
16 rows in set (0.00 sec)
*/

SELECT DATE_FORMAT(t,'%M %e, %Y') AS 'Date of message', CONCAT(srcuser,'@',srchost) AS 'Message sender', size AS 'Number of bytes' FROM mail;
/*
+-----------------+----------------+-----------------+
| Date of message | Message sender | Number of bytes |
+-----------------+----------------+-----------------+
| May 11, 2014    | barb@saturn    |           58274 |
| May 12, 2014    | tricia@mars    |          194925 |
| May 12, 2014    | phil@mars      |            1048 |
| May 12, 2014    | barb@saturn    |             271 |
| May 14, 2014    | gene@venus     |            2291 |
| May 14, 2014    | phil@mars      |            5781 |
| May 14, 2014    | barb@venus     |           98151 |
| May 14, 2014    | tricia@saturn  |         2394482 |
| May 15, 2014    | gene@mars      |            3824 |
| May 15, 2014    | phil@venus     |             978 |
| May 15, 2014    | gene@mars      |          998532 |
| May 15, 2014    | gene@saturn    |            3856 |
| May 16, 2014    | gene@venus     |             613 |
| May 16, 2014    | phil@venus     |           10294 |
| May 19, 2014    | phil@mars      |             873 |
| May 19, 2014    | gene@saturn    |           23992 |
+-----------------+----------------+-----------------+
16 rows in set (0.00 sec)
*/

SELECT 1 AS 'INTEGER';
/*
+---------+
| INTEGER |
+---------+
|       1 |
+---------+
1 row in set (0.00 sec)
*/

SELECT t, srcuser, dstuser, size/1024 AS kilobytes FROM mail WHERE size/1024 > 500;
/*
+---------------------+---------+---------+-----------+
| t                   | srcuser | dstuser | kilobytes |
+---------------------+---------+---------+-----------+
| 2014-05-14 17:03:01 | tricia  | phil    | 2338.3613 |
| 2014-05-15 10:25:52 | gene    | tricia  |  975.1289 |
+---------------------+---------+---------+-----------+
2 rows in set (0.00 sec)
*/