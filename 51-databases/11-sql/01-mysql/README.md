# MySql

https://sites.google.com/site/williamhandev/database/mysql/jump-start

## Installation

> stop mysql server

System Preferences - MySQL

> uninstall

http://community.jaspersoft.com/wiki/uninstall-mysql-mac-os-x

> install using native packages

MySQL Community Server

1, https://dev.mysql.com/downloads/mysql/

2, download `Mac OS X 10.12 (x86, 64-bit), DMG Archive`

3, follow link below to install

https://dev.mysql.com/doc/refman/5.6/en/osx-installation-pkg.html

> login as root the first time using the temp password

$ /usr/local/mysql/bin/mysql -u root -p

> reset password

https://dev.mysql.com/doc/refman/5.5/en/resetting-permissions.html

mysql> SET PASSWORD FOR 'root'@'localhost' = PASSWORD('root');

so we can login as { user: 'root', password: 'root' } in the future

> Create database

mysql> create database INVENTORY;
mysql> show databases;

> grant all privileges to inventory database

mysql> GRANT ALL PRIVILEGES ON INVENTORY.* TO 'user'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
quit
mysql> \q
Select database

> login as username=community

$ mysql -u user -p <- password is password
mysql> use INVENTORY;    <- select database
mysql> show tables;   <- show tables
Empty set (0.00 sec)
mysql> SELECT DATABASE() FROM DUAL;   <- show current database

> create table

mysql> CREATE TABLE customer (name VARCHAR(20), age INT);
mysql> SHOW TABLES;
+---------------------+
| Tables_in_inventory |
+---------------------+
| customer            |
+---------------------+
1 row in set (0.00 sec)

> insert data

mysql> INSERT INTO customer (name, age) VALUES("Tom", 10);
mysql> INSERT INTO customer (name, age) VALUES("Dick", 20);
mysql> INSERT INTO customer (name, age) VALUES("Harry", 18);

> select data
mysql> SELECT * FROM INVENTORY.customer;

> drop database

mysql> DROP DATABASE INVENTORY;

## Start/Stop Server

System Preferences

$ mysql.server start
$ mysql.server stop
$ sudo /usr/local/mysql/support-files/mysql.server start
$ sudo /usr/local/mysql/support-files/mysql.server stop

$ mysqladmin version   <- shows version info if it is running
$ mysql --version

$ type -a mysql   <- find out where MYSQL is installed
mysql is /usr/local/mysql/bin/mysql
