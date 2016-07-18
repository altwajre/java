
SELECT * FROM profile_contact ORDER BY profile_id, service;
/*
+------------+----------+--------------+
| profile_id | service  | contact_name |
+------------+----------+--------------+
|          1 | Fackbook | user1-fbid   |
|          1 | Twitter  | user1-twtrid |
|          2 | Fackbook | user2-msnid  |
|          2 | LinkedIn | user2-msnid  |
|          2 | Twitter  | user2-fbrid  |
|          4 | LinkedIn | user4-lnkdid |
+------------+----------+--------------+
6 rows in set (0.00 sec)
*/

SELECT id, name, service, contact_name FROM profile INNER JOIN profile_contact ON id = profile_id;
/*
+----+---------+----------+--------------+
| id | name    | service  | contact_name |
+----+---------+----------+--------------+
|  1 | Sybil   | Fackbook | user1-fbid   |
|  1 | Sybil   | Twitter  | user1-twtrid |
|  2 | Nancy   | Fackbook | user2-msnid  |
|  2 | Nancy   | LinkedIn | user2-msnid  |
|  2 | Nancy   | Twitter  | user2-fbrid  |
|  4 | Lothair | LinkedIn | user4-lnkdid |
+----+---------+----------+--------------+
6 rows in set (0.00 sec)
*/

SELECT * FROM profile_contact WHERE profile_id = (SELECT id FROM profile WHERE name = 'Nancy');
/*
+------------+----------+--------------+
| profile_id | service  | contact_name |
+------------+----------+--------------+
|          2 | Fackbook | user2-msnid  |
|          2 | LinkedIn | user2-msnid  |
|          2 | Twitter  | user2-fbrid  |
+------------+----------+--------------+
3 rows in set (0.00 sec)
*/
