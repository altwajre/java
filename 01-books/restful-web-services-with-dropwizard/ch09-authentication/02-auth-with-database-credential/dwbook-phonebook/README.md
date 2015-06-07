# authentication-auth-with-database-credential - dwbook-phonebook

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

## Client

### curl

> $ curl http://localhost:8080/contact/1

```
$ curl http://localhost:8080/contact/1
Credentials are required to access this resource.
```

### Visit

> http://localhost:8080/contact/1 and login

```
username: will
password: pass
```

> http://localhost:8080/contact/all

```
[
{
id: 1,
firstName: "John",
lastName: "Doe",
phone: "+123456789"
},
{
id: 2,
firstName: "Jane",
lastName: "Doe",
phone: "+987654321"
}
]
```

> http://localhost:8080/client/showContact?id=1

```
ID: 1
First name: John
Last name: Doe
Phone: +123456789
```
