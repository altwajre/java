# rest-api app and testing

## mysql

docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)

docker run --name mysql \
-e MYSQL_ROOT_PASSWORD=test \
-e MYSQL_DATABASE=phonebook \
-e MYSQL_USER=test \
-e MYSQL_PASSWORD=test \
-p 3306:3306 \
-d mysql

docker exec -it mysql bash
mysql -u root -p
Enter password: test
mysql> show databases;
mysql> GRANT ALL PRIVILEGES ON test.* TO 'test'@'%' IDENTIFIED BY 'test';
mysql> USE phonebook;

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

mysql> INSERT INTO `contact` VALUES (NULL, 'John', 'Doe', '+123456789'), (NULL, 'Jane', 'Doe', '+987654321');
mysql> select * from phonebook.contact;

## Server

> `cd 01-server`

### Intellij

App.main()

### Make package

> `mvn package` or `mvn clean install`

### Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## Client

### Run Test

#### 04-testng-client-json-config

> `mvn package`

> `export TEST_ENV=local; java -cp target/app-1.0-SNAPSHOT.jar com.company.app.TestRunner`

#### 05-testng-client-yaml-config

> `mvn package`

> `java -jar target/app-1.0-SNAPSHOT.jar config.local.yml`

==========================================================================================

### cURL HTTP requests

#### DELETE request

> `$ curl -X GET http://localhost:8080/contact/all`

> `$ curl -X DELETE http://localhost:8080/contact/3`

> `$ curl -X GET http://localhost:8080/contact/3`

### Browser - visit

> Open chrome, developer tool, Network

#### GET

> http://localhost:8080/client/showContact?id=1

```
ID: 1
First name: John
Last name: Doe
Phone: +123456789
```

## Test

### Jersey Rest Client vs Apache HttpClient

> http://stackoverflow.com/questions/15214252/is-jersey-rest-client-more-optimal-than-httpclient-for-calling-a-restful-service

### Jersey Rest Client

> `cd 02-jersey-rest-client`

### Run tests

> In intellij, right click on test and select run test

> `mvn package`

### Apache HttpClient

> `cd 03-apache-httpclient`

### Run tests

> In intellij, right click on test and select run test

> `mvn package`

