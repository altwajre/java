# Diff examples

https://javers.org/documentation/diff-examples/

examples are under test

## mysql

docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)

docker run --name mysql \
-e MYSQL_ROOT_PASSWORD=test \
-e MYSQL_DATABASE=test \
-e MYSQL_USER=test \
-e MYSQL_PASSWORD=test \
-p 3306:3306 \
-d mysql

docker exec -it mysql bash
mysql -u root -p
Enter password: test
mysql> show databases;
mysql> CREATE DATABASE test;
mysql> use test;

```
DROP TABLE Customer;
DROP TABLE currency;
TRUNCATE TABLE Customer;
TRUNCATE TABLE currency;

CREATE TABLE `Customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `currency` (
  `NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `SYMBOL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`COUNTRY_NAME`)
);
```

mysql> GRANT ALL PRIVILEGES ON test.* TO 'test'@'%' IDENTIFIED BY 'test';
- after curl post
mysql> use test;
mysql> SELECT * FROM Customer;

## java -jar on local machine

> command line

mvn clean package
java -jar target/vertx-mysql-1.0-SNAPSHOT.jar

> intellij

CompareDatabaseSnapshotApp.main()
