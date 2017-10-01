
SET @date = '2015-07-21';
SELECT @date, LEFT(@date,4) AS year, MID(@date,6,2) AS month, RIGHT(@date,2) AS day;
/*
+------------+------+-------+------+
| @date      | year | month | day  |
+------------+------+-------+------+
| 2015-07-21 | 2015 | 07    | 21   |
+------------+------+-------+------+
1 row in set (0.00 sec)
*/

SELECT @date, SUBSTRING(@date,6), MID(@date,6);
/*
+------------+--------------------+--------------+
| @date      | SUBSTRING(@date,6) | MID(@date,6) |
+------------+--------------------+--------------+
| 2015-07-21 | 07-21              | 07-21        |
+------------+--------------------+--------------+
1 row in set (0.00 sec)
*/

SET @email = 'postmaster@example.com';
SELECT @email, SUBSTRING_INDEX(@email,'@',1) AS user, SUBSTRING_INDEX(@email,'@',-1) AS host;
/*
Use SUBSTRING_INDEX(str,c,n) to return everything to the right or left of a given character.
If the third arg is negative, returns everything to the right of the character.
+------------------------+------------+-------------+
| @email                 | user       | host        |
+------------------------+------------+-------------+
| postmaster@example.com | postmaster | example.com |
+------------------------+------------+-------------+
1 row in set (0.00 sec)
*/

SELECT name from metal WHERE LEFT(name,1) >= 'n';
/*
Finds metal names have a first letter that lies in the last half of the alphabet
+----------+
| name     |
+----------+
| platinum |
| tin      |
+----------+
2 rows in set (0.00 sec)
*/

SELECT CONCAT(name,' ends in "d": ',IF(RIGHT(name,1)='d','YES','NO')) AS 'ends in "d"?' FROM metal;
/*
Combine strings using CONCAT() function
+--------------------------+
| ends in "d"?             |
+--------------------------+
| gold ends in "d": YES    |
| iron ends in "d": NO     |
| lead ends in "d": YES    |
| mercury ends in "d": NO  |
| platinum ends in "d": NO |
| tin ends in "d": NO      |
+--------------------------+
6 rows in set (0.00 sec)
*/

UPDATE metal SET name = CONCAT(name,'ide');
SELECT name FROM metal;
/*
Modify column values "in place"
+-------------+
| name        |
+-------------+
| goldide     |
| ironide     |
| leadide     |
| mercuryide  |
| platinumide |
| tinide      |
+-------------+
6 rows in set (0.00 sec)
*/

UPDATE metal SET name = LEFT(name,CHAR_LENGTH(name)-3);
SELECT name FROM metal;
/*
To undo the operation, strip the last three characters
+----------+
| name     |
+----------+
| gold     |
| iron     |
| lead     |
| mercury  |
| platinum |
| tin      |
+----------+
6 rows in set (0.00 sec)
*/
