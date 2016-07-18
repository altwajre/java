
SELECT name, LOCATE('in',name), LOCATE('in',name,3) FROM metal;
/*
Takes two args representing substring you're looking for. The return value is the position at the substring occurs.
An optional third arg indicate position within the string at which to start looking.
+----------+-------------------+---------------------+
| name     | LOCATE('in',name) | LOCATE('in',name,3) |
+----------+-------------------+---------------------+
| gold     |                 0 |                   0 |
| iron     |                 0 |                   0 |
| lead     |                 0 |                   0 |
| mercury  |                 0 |                   0 |
| platinum |                 5 |                   5 |
| tin      |                 2 |                   0 |
+----------+-------------------+---------------------+
6 rows in set (0.00 sec)
*/

SELECT name, name LIKE '%in%', name REGEXP 'in' FROM metal;
/*
Determine only whether the substring is present
+----------+------------------+------------------+
| name     | name LIKE '%in%' | name REGEXP 'in' |
+----------+------------------+------------------+
| gold     |                0 |                0 |
| iron     |                0 |                0 |
| lead     |                0 |                0 |
| mercury  |                0 |                0 |
| platinum |                1 |                1 |
| tin      |                1 |                1 |
+----------+------------------+------------------+
6 rows in set (0.00 sec)
*/