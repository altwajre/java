# Compound primary keys

## mysql

docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)

docker run --name mysql-5 \
-e MYSQL_ROOT_PASSWORD=test \
-e MYSQL_DATABASE=test \
-e MYSQL_USER=test \
-e MYSQL_PASSWORD=test \
-p 3306:3306 \
-d mysql:5.7.23

docker exec -it mysql-5 bash
mysql -u root -p
Enter password: test
mysql> show databases;

CREATE DATABASE ifinances;
use ifinances;

CREATE TABLE `currency` (
  `NAME` varchar(45) NOT NULL,
  `COUNTRY_NAME` varchar(45) NOT NULL,
  `SYMBOL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NAME`,`COUNTRY_NAME`)
);

GRANT ALL PRIVILEGES ON ifinances.* TO 'test'@'%' IDENTIFIED BY 'test';
SELECT * FROM currency;
