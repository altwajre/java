
SELECT USER(), CHARSET(USER()), COLLATION(USER());
/*
+------------------+-----------------+-------------------+
| USER()           | CHARSET(USER()) | COLLATION(USER()) |
+------------------+-----------------+-------------------+
| cbuser@localhost | utf8            | utf8_general_ci   |
+------------------+-----------------+-------------------+
1 row in set (0.00 sec)
*/

SET NAMES 'latin1'; # change configuration
SELECT CHARSET('abc'), COLLATION('abc');
/*
+----------------+-------------------+
| CHARSET('abc') | COLLATION('abc')  |
+----------------+-------------------+
| latin1         | latin1_swedish_ci |
+----------------+-------------------+
1 row in set (0.00 sec)
*/

SET NAMES utf8 COLLATE 'utf8_bin';
SELECT CHARSET('abc'), COLLATION('abc');
/*
+----------------+------------------+
| CHARSET('abc') | COLLATION('abc') |
+----------------+------------------+
| utf8           | utf8_bin         |
+----------------+------------------+
1 row in set (0.01 sec)
*/

SET @s1 = _latin1 'my string', @s2 = CONVERT(@s1 USING utf8); # convert a string
SELECT CHARSET(@s1), CHARSET(@s2);
/*
+--------------+--------------+
| CHARSET(@s1) | CHARSET(@s2) |
+--------------+--------------+
| latin1       | utf8         |
+--------------+--------------+
1 row in set (0.00 sec)
*/

SET @s1 = _latin1 'my string', @s2 = @s1 COLLATE latin1_spanish_ci; # change string collation
SELECT COLLATION(@s1), COLLATION(@s2);
/*
+-------------------+-------------------+
| COLLATION(@s1)    | COLLATION(@s2)    |
+-------------------+-------------------+
| latin1_swedish_ci | latin1_spanish_ci |
+-------------------+-------------------+
1 row in set (0.00 sec)
*/