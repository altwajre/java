# Compound primary keys

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
mysql> CREATE DATABASE ifinances;
mysql> use ifinances;

```
CREATE TABLE `currency` (
  `NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `SYMBOL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`COUNTRY_NAME`)
);
```
mysql> GRANT ALL PRIVILEGES ON ifinances.* TO 'test'@'%' IDENTIFIED BY 'test';
- after curl post
mysql> use test;
mysql> SELECT * FROM currency;
