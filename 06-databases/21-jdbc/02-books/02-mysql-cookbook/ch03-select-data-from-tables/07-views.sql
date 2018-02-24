
SELECT DATE_FORMAT(t, '%M %e, %Y') AS date_sent, CONCAT(srcuser,'@',srchost) AS sender, CONCAT(dstuser,'@',dsthost) AS recipient, size FROM mail;
/*
+--------------+---------------+---------------+---------+
| date_sent    | sender        | recipient     | size    |
+--------------+---------------+---------------+---------+
| May 11, 2014 | barb@saturn   | tricia@mars   |   58274 |
| May 12, 2014 | tricia@mars   | gene@venus    |  194925 |
| May 12, 2014 | phil@mars     | phil@saturn   |    1048 |
| May 12, 2014 | barb@saturn   | tricia@venus  |     271 |
| May 14, 2014 | gene@venus    | barb@mars     |    2291 |
| May 14, 2014 | phil@mars     | tricia@saturn |    5781 |
| May 14, 2014 | barb@venus    | barb@venus    |   98151 |
| May 14, 2014 | tricia@saturn | phil@venus    | 2394482 |
| May 15, 2014 | gene@mars     | gene@saturn   |    3824 |
| May 15, 2014 | phil@venus    | phil@venus    |     978 |
| May 15, 2014 | gene@mars     | tricia@saturn |  998532 |
| May 15, 2014 | gene@saturn   | gene@mars     |    3856 |
| May 16, 2014 | gene@venus    | barb@mars     |     613 |
| May 16, 2014 | phil@venus    | barb@venus    |   10294 |
| May 19, 2014 | phil@mars     | tricia@saturn |     873 |
| May 19, 2014 | gene@saturn   | gene@venus    |   23992 |
+--------------+---------------+---------------+---------+
16 rows in set (0.00 sec)
*/

CREATE VIEW mail_view AS SELECT DATE_FORMAT(t, '%M %e, %Y') AS date_sent, CONCAT(srcuser,'@',srchost) AS sender, CONCAT(dstuser,'@',dsthost) AS recipient, size FROM mail;

SELECT date_sent, sender, size FROM mail_view WHERE size > 100000 ORDER BY size;
/*
+--------------+---------------+---------+
| date_sent    | sender        | size    |
+--------------+---------------+---------+
| May 12, 2014 | tricia@mars   |  194925 |
| May 15, 2014 | gene@mars     |  998532 |
| May 14, 2014 | tricia@saturn | 2394482 |
+--------------+---------------+---------+
3 rows in set (0.00 sec)
*/

