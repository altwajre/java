# interact-with-database-by-using-mapresultasbean - dwbook-phonebook

maven-archetype-quickstart project

```
The @MapResultAsBean annotation
In this example, we have implemented a mapper and used the @Mapper annotation in order to map the result of an SQL query to a Contact instance. 
An alternative approach would be the use of the MapResultAsBean annotation.

@MapResultAsBean
@SqlQuery("select * from contact where id= :id")
Contact getContactById(@Bind("id") int id);
By annotating #getContactById() in this example, we map the result of the SQL query directly to a Contact instance, without needing to implement a custom mapper. 
In order for this to work though, the Contact class should be updated with setters (that is, setFirstName(String firstName){ .. }). 
Due to this, the final keyword will have to be removed from the declaration of each member variable.
```

## Start mysql server

> `mysql.server start`

> `mysql.server stop` when you are done

## Create database

> `$ sudo /usr/local/bin/mysql -u root` and enter password

> `mysql> drop table contact`;

> `mysql> drop database phonebook`;

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

## Make package

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## cURL HTTP requests

### DELETE request

> `$ curl http://localhost:8080/contact/all`

> `$ curl -X DELETE http://localhost:8080/contact/3`

> `$ curl -X GET http://localhost:8080/contact/3`

### GET request

> `$ curl http://localhost:8080/contact/1`

```
$ curl http://localhost:8080/contact/1
{"id":1,"firstName":"John","lastName":"Doe","phone":"+123456789"}
```

> `$ curl http://localhost:8080/contact/all`

```
$ curl http://localhost:8080/contact/all
[{"id":1,"firstName":"John","lastName":"Doe","phone":"+123456789"},{"id":2,"firstName":"Jane","lastName":"Doe","phone":"+987654321"}]
```

### POST request

> `$ curl --verbose --header "Content-Type: application/json" -X POST -d '{"firstName": "FOO", "lastName":"BAR", "phone":"987654321"}' http://localhost:8080/contact/`

```
$ curl --verbose --header "Content-Type: application/json" -X POST -d '{"firstName": "FOO", "lastName":"BAR", "phone":"987654321"}' http://localhost:8080/contact/
* Hostname was NOT found in DNS cache
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> POST /contact/ HTTP/1.1
> User-Agent: curl/7.37.1
> Host: localhost:8080
> Accept: */*
> Content-Type: application/json
> Content-Length: 59
> 
* upload completely sent off: 59 out of 59 bytes
< HTTP/1.1 201 Created
< Date: Sat, 06 Jun 2015 04:59:01 GMT
< Location: http://localhost:8080/3
< Content-Length: 0
< 
* Connection #0 to host localhost left intact
```

> `$ curl http://localhost:8080/contact/all`

```
$ curl http://localhost:8080/contact/all
[{"id":1,"firstName":"John","lastName":"Doe","phone":"+123456789"},{"id":2,"firstName":"Jane","lastName":"Doe","phone":"+987654321"},{"id":3,"firstName":"FOO","lastName":"BAR","phone":"987654321"}]
```

> `$ curl -X DELETE http://localhost:8080/contact/3`

> `$ curl -X GET http://localhost:8080/contact/3`
