# ui-view - dwbook-phonebook

maven-archetype-quickstart project

## Start mysql server

> `$ mysql.server start`

> `$ mysql.server stop` when you are done

## Create database

> `$ sudo /usr/local/bin/mysql -u root` and enter password

> `mysql> drop database phonebook`;

> `mysql> drop table contact`;

> `mysql> CREATE DATABASE phonebook`;

> `mysql> CREATE USER 'phonebookuser'@'localhost' IDENTIFIED BY'phonebookpassword'`;

> `mysql> GRANT ALL ON phonebook.* TO 'phonebookuser'@'localhost'`;

> `mysql> USE phonebook;`

> create table

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

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## Client - view

### Visit

> http://localhost:8080/client/showContact?id=2

```
Contact (2)
First Name	Jane
Last Name	Doe
Phone	+987654321
```
