/*
A temporary table can have the same name as a permanent table. In this case, the temporary table “hides” the permanent
table for the duration of its existence, which can be useful for making a copy of a table that you can modify without
affecting the original by mistake.
*/
CREATE TEMPORARY TABLE mail SELECT * FROM mail;
# display temp table
SELECT COUNT(*) FROM mail;
/*
+----------+
| COUNT(*) |
+----------+
|       16 |
+----------+
1 row in set (0.00 sec)
*/

DELETE FROM mail;
SELECT COUNT(*) FROM mail;
/*
+----------+
| COUNT(*) |
+----------+
|        0 |
+----------+
1 row in set (0.00 sec)
*/

DROP TEMPORARY TABLE mail;
SELECT COUNT(*) FROM mail;
/*
+----------+
| COUNT(*) |
+----------+
|       16 |
+----------+
1 row in set (0.00 sec)
*/