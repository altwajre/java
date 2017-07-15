# SQL

## Start MySQL Server

> Start MySql Server at `System Preferences`, `MySql`

### Command line

> `sudo /usr/local/mysql/bin/mysqld -u root` <- # START SERVER #

> `sudo /usr/local/mysql/support-files/mysql.server start`

> `sudo /usr/local/mysql/support-files/mysql.server stop` <- # STOP SERVER #

> if you have Access denied for user error, do following

```
$ sudo /usr/local/mysql/bin/mysql -u root -pabc  <- password is "abc"; -p is option
re-add local user
mysql> DROP USER 'whan'@'localhost';  <- drop user
mysql> CREATE USER 'whan'@'localhost'; <- run it if local-user is not in user list
mysql> GRANT ALL ON cookbook.* TO 'whan'@'localhost';  <- Grant user to cookbook database
```

## Error solutions

- Error Code: 1175. You are using safe update mode and you tried to update a table without a WHERE that uses a KEY column To disable safe mode

in workbench

```
SET SQL_SAFE_UPDATES = 0;
```
