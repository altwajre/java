# ui-view - dwbook-phonebook

maven-archetype-quickstart project

## Start mysql server

> `$ mysql.server start`

> `$ mysql.server stop` when you are done

> `$ sudo /usr/local/bin/mysql -u root` and login

> select database

```
mysql> use phonebook;
```

> create table

```
mysql> CREATE TABLE IF NOT EXISTS `users` (
        `username` varchar(20) NOT NULL,
        `password` varchar(255) NOT NULL,
        PRIMARY KEY (`username`)
       ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;
Query OK, 0 rows affected (0.01 sec)
```

> insert record

```
INSERT INTO `users` VALUES ('will', 'pass');
```

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
