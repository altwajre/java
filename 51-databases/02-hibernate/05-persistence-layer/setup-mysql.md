# MySQL

## docker container

- Create container named `mysql`
docker run --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest
- Login into container `mysql`
docker exec -it mysql bash
- Login into `mysql` container
mysql -u root -p # password: root

## SQL

- Create database and add user
show databases;
create database ifinances;
GRANT ALL PRIVILEGES ON ifinances.* TO 'infinite'@'%' IDENTIFIED BY 'skills' WITH GRANT OPTION;
use ifinances;
- Create table
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `finances_user`;
SET FOREIGN_KEY_CHECKS=1;
CREATE TABLE `finances_user` (
  `USER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FIRST_NAME` varchar(45) NOT NULL,
  `LAST_NAME` varchar(45) NOT NULL,
  `BIRTH_DATE` date NOT NULL,
  `EMAIL_ADDRESS` varchar(100) NOT NULL,
  `LAST_UPDATED_BY` varchar(45) NOT NULL,
  `LAST_UPDATED_DATE` datetime NOT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `USER_ADDRESS_LINE_1` varchar(100) DEFAULT NULL,
  `USER_ADDRESS_LINE_2` varchar(100) DEFAULT NULL,
  `CITY` varchar(100) DEFAULT NULL,
  `STATE` varchar(2) DEFAULT NULL,
  `ZIP_CODE` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
);

## App

- Run App in Intellij
In `02-jpa-dao`, run App.man()

## Workbench

User: infinite
Password: skills

use ifinances;
TRUNCATE TABLE finances_user;
select * from finances_user;
 
## Docker Cleanup

```
# List all running containers
docker ps
# List all containers includes stopped ones
docker ps -a
# List all images
docker images
 
# Stop container
docker stop commerce-product-8080
 
# Delete all containers
docker rm $(docker ps -a -q)
# Force delete all containers
docker rm -f $(docker ps -a -q)
 
# Delete all images
docker rmi $(docker images -q)
# Force delete all images
docker rmi -f $(docker images -q)
```
