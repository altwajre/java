# MySQL

## docker container

> First time download docker image and docker run

docker run --name mysql_local -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:5.7.23

https://hub.docker.com/_/mysql/

docker run --name mysql-5 \
-e MYSQL_ROOT_PASSWORD=root \
-e MYSQL_DATABASE=test \
-p 3306:3306 \
-d mysql:5.7.23

docker stop <containerId>
docker start <containerId>

> login to docker container to check database

docker exec -it mysql-5 sh

mysql -u root -p
Enter password: <- test

mysql> show databases;
create database ifinances;
GRANT ALL PRIVILEGES ON ifinances.* TO 'infinite'@'%' IDENTIFIED BY 'skills' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON ifinances.* TO 'infinite'@'%' IDENTIFIED BY 'skills';
use ifinances;

> Workbench
User: infinite
Password: skills

use ifinances;
run InfiniteFinancesSchema.sql in mysql workbench

## cleanup

- Force delete all containers
docker rm -f $(docker ps -a -q)
- Force delete all images
docker rmi -f $(docker images -q)
