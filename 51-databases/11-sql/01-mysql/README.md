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

> brew install

https://coderwall.com/p/os6woq/uninstall-all-those-broken-versions-of-mysql-and-re-install-it-with-brew-on-mac-mavericks

> Start Server

$ mysql.server start
$ mysql.server stop
$ sudo /usr/local/mysql/support-files/mysql.server start
$ sudo /usr/local/mysql/support-files/mysql.server stop

$ mysqladmin version   <- shows version info if it is running
$ mysql --version

$ type -a mysql   <- find out where MYSQL is installed
mysql is /usr/local/mysql/bin/mysql

## MySql command mode

> login as root

$ /usr/local/mysql/bin/mysql -u root -p

> add user

mysql> DROP USER 'whan'@'localhost';  <- drop user
mysql> CREATE USER 'whan'@'localhost'; <- run it if local-user is not in user list
$ mysql
mysql>

> grant user to database access

mysql> GRANT ALL ON cookbook.* TO 'whan'@'localhost';  <- Grant user to cookbook database

> Create database

mysql> create database INVENTORY;
mysql> show databases;

> grant all privileges to inventory database

mysql> GRANT ALL PRIVILEGES ON INVENTORY.* TO 'community'@'%' IDENTIFIED BY 'mysql01' WITH GRANT OPTION;

> username=community; password=mysql01;

mysql> GRANT ALL PRIVILEGES ON INVENTORY.* TO 'community'@'localhost' IDENTIFIED BY 'mysql01' WITH GRANT OPTION;
    
setup the users that just created for the particular database
mysql> FLUSH PRIVILEGES;
quit
mysql> \q
Select database

> login as username=community

$ mysql -u community -p
mysql> use INVENTORY;    <- select table
mysql> show tables;   <- show tables
mysql> SELECT DATABASE() FROM DUAL;   <- show current database
Create tables

mysql> CREATE TABLE customer (name VARCHAR(20), age INT);
mysql> SHOW TABLES;
Insert data

mysql> INSERT INTO customer (name, age) VALUES("Tom", 10);
mysql> INSERT INTO customer (name, age) VALUES("Dick", 20);
mysql> INSERT INTO customer (name, age) VALUES("Harry", 18);
Select data

mysql> SELECT * FROM INVENTORY.customer;
Drop database

mysql> DROP DATABASE INVENTORY;
