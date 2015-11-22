# prepare-database

## Installation

> `brew update`, `brew doctor`, `brew upgrade`

> `brew install mysql`

## Start Server

> `mysql.server start`

> `mysql.server stop` when you are done

## Open mysql

> `sudo /usr/local/bin/mysql -u root` and enter `password` <-- need it when `CREATE DATABASE tmp;`

> `mysql` <-- works for SELECT statement

## Create database

> `mysql> SHOW DATABASES;`

> `mysql> SHOW TABLES;`

> `mysql> drop database phonebook;`

> `mysql> drop table contact;`

> `mysql> CREATE DATABASE phonebook;`

> `mysql> CREATE USER 'phonebookuser'@'localhost' IDENTIFIED BY'phonebookpassword';`

> `mysql> GRANT ALL ON phonebook.* TO 'phonebookuser'@'localhost';`

> `mysql> USE phonebook;`

### Create table

```
CREATE TABLE IF NOT EXISTS `contact` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `firstName` varchar(255) NOT NULL,
      `lastName` varchar(255) NOT NULL,
      `phone` varchar(30) NOT NULL,
      PRIMARY KEY (`id`)
      ) 
      ENGINE=InnoDB 
      DEFAULT CHARSET=utf8 
      AUTO_INCREMENT=1;
```

> `mysql> INSERT INTO `contact` VALUES (NULL, 'John', 'Doe', '+123456789'), (NULL, 'Jane', 'Doe', '+987654321');`

> `mysql> select * from phonebook.contact;`

