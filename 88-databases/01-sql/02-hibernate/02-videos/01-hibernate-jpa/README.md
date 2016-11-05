# Hibernate and Java Persistence API (JPA) Fundamentals

https://www.safaribooksonline.com/library/view/hibernate-and-java/9781771373494/

## Start MySQL Server

> Start MySql Server at `System Preferences`, `MySql`

### Command line

> `sudo /usr/local/mysql/bin/mysqld -u root` <-- ### START SERVER ###

> `sudo /usr/local/mysql/support-files/mysql.server start`

> `sudo /usr/local/mysql/support-files/mysql.server stop`

> if you have Access denied for user error, do following

```
$ sudo /usr/local/mysql/bin/mysql -u root -pabc  <- password is "abc"; -p is option
re-add local user
mysql> DROP USER 'user'@'localhost';  <- drop user
mysql> CREATE USER 'user'@'localhost'; <- run it if local-user is not in user list
mysql> GRANT ALL ON cookbook.* TO 'user'@'localhost';  <- Grant user to cookbook database
```

## Workbench

database connection

```
ifinances
```

select from one of tables

```
use ifinances;
select * from FINANCES_USER;
```
