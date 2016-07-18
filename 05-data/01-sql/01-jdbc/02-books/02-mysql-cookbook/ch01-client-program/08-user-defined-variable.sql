
# assign a value to a user variable, then refers to that variable later.
SELECT @max_limbs := MAX(arms+legs) FROM limbs;
SELECT @max_limbs;
SELECT * FROM limbs WHERE arms+legs = @max_limbs;

# save a result from function to a variable
SELECT @last_id := LAST_INSERT_ID();
SELECT @last_id;

# variable holds a single value. the last row is assigned when multiple rows are returned.
SELECT @name := thing FROM limbs WHERE legs = 0;
/*
+----------------+
| @name := thing |
+----------------+
| squid          |
| fish           |
| phonograph     |
+----------------+
*/
SELECT @name;
/*
+------------+
| @name      |
+------------+
| phonograph |
+------------+
*/

# no assignment when no row is returned, and the variable retains its previous value.
# if the variable has not been used previously, its value is NULL.
SELECT @name2 := thing FROM limbs WHERE legs < 0;
/*
Empty set (0.00 sec)
*/

SELECT @name2;
/*
+--------+
| @name2 |
+--------+
| NULL   |
+--------+
1 row in set (0.00 sec)
*/

# set a variable to a value using := or = for arithmetic operators assignment
SET @sum = 4 + 7;
SELECT @sum;

# assign a SELECT result to a variable
SET @max_limbs = (SELECT MAX(arms+legs) FROM limbs);

# user variable names are not case sensitive
SET @x = 1, @X = 2; SELECT @x, @X;
/*
+------+------+
| @x   | @X   |
+------+------+
|    2 |    2 |
+------+------+
*/