/*
login as root alternatives
$ sudo /usr/local/mysql/bin/mysql -u root -pabc  <- password is "abc"; -p is option
$ mysql -h localhost -u root
optional: Enter password: *****

re-add local user
mysql> DROP USER 'whan'@'localhost';  <- drop user
mysql> CREATE USER 'whan'@'localhost'; <- run it if local-user is not in user list
mysql> GRANT ALL ON cookbook.* TO 'whan'@'localhost';  <- Grant user to cookbook database

# login as admin root
$ mysql -u root
*/

SELECT user FROM mysql.user;  <- show users
CREATE USER 'cbuser'@'localhost' IDENTIFIED BY 'cbpass';
GRANT ALL ON cookbook.* TO 'cbuser'@'localhost'; -- Grant cbuser to cookbook database

/*
# login as cbuser
$ mysql -u cbuser -p
Enter password: cbpass
*/

SELECT user();
/*
+------------------+
| user()           |
+------------------+
| cbuser@localhost |
+------------------+
*/
