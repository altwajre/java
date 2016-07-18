
SELECT 'cat' = 'cat', 'cat' = 'dog', 'cat' <> 'cat', 'cat' <> 'dog';
/*
+---------------+---------------+----------------+----------------+
| 'cat' = 'cat' | 'cat' = 'dog' | 'cat' <> 'cat' | 'cat' <> 'dog' |
+---------------+---------------+----------------+----------------+
|             1 |             0 |              0 |              1 |
+---------------+---------------+----------------+----------------+
1 row in set (0.00 sec)
*/

SELECT 'cat' < 'awk', 'cat' < 'dog', 'cat' BETWEEN 'awk' AND 'eel';
/*
+---------------+---------------+-------------------------------+
| 'cat' < 'awk' | 'cat' < 'dog' | 'cat' BETWEEN 'awk' AND 'eel' |
+---------------+---------------+-------------------------------+
|             0 |             1 |                             1 |
+---------------+---------------+-------------------------------+
1 row in set (0.00 sec)
*/

SET @s1 = BINARY 'cat', @s2 = BINARY 'CAT';
SELECT @s1 = @s2;
/*
unequal
+-----------+
| @s1 = @s2 |
+-----------+
|         0 |
+-----------+
1 row in set (0.00 sec)
*/

SET @s1 = CONVERT(@s1 USING latin1) COLLATE latin1_swedish_ci;
SET @s2 = CONVERT(@s2 USING latin1) COLLATE latin1_swedish_ci;
SELECT @s1 = @s2;
/*
equal when compared as case-insensitive nonbinary strings
+-----------+
| @s1 = @s2 |
+-----------+
|         1 |
+-----------+
1 row in set (0.00 sec)
*/

SET @s1 = CONVERT(@s1 USING latin1);
SET @s2 = CONVERT(@s2 USING latin1);
SELECT @s1 = @s2;
/*
equal because latin1_swedish_ci is the default collation for latin1
+-----------+
| @s1 = @s2 |
+-----------+
|         1 |
+-----------+
1 row in set (0.00 sec)
*/

SET @s1 = _latin1 'cat', @s2 = _latin1 'CAT';
SELECT @s1 = @s2;
/*
+-----------+
| @s1 = @s2 |
+-----------+
|         1 |
+-----------+
1 row in set (0.00 sec)
*/