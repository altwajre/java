
SELECT "I'm asleep", 'He said, "Boo!"';
/*
Enclose a string containing single quotes within double quotes when ANSI_QUOTES is disabled
or enclose a string containing double quotes within single quotes
+------------+-----------------+
| I'm asleep | He said, "Boo!" |
+------------+-----------------+
| I'm asleep | He said, "Boo!" |
+------------+-----------------+
1 row in set (0.00 sec)
*/

SELECT 'I''m asleep', 'I\'m wide awake';
/*
Include a quote character within a string quoted by the same kind of quote, double the quote
or precede it with a backslash.
+------------+----------------+
| I'm asleep | I'm wide awake |
+------------+----------------+
| I'm asleep | I'm wide awake |
+------------+----------------+
1 row in set (0.00 sec)
*/
SELECT "He said, ""Boo!""", "And I said, \"Yikes!\"";
/*
+-----------------+----------------------+
| He said, "Boo!" | And I said, "Yikes!" |
+-----------------+----------------------+
| He said, "Boo!" | And I said, "Yikes!" |
+-----------------+----------------------+
1 row in set (0.00 sec)
*/

SELECT 'Install MySQL in C:\\mysql on Windows';
/*
A backslash turns off any special meaning of the following character, including itself.
+--------------------------------------+
| Install MySQL in C:\mysql on Windows |
+--------------------------------------+
| Install MySQL in C:\mysql on Windows |
+--------------------------------------+
1 row in set (0.00 sec)
*/