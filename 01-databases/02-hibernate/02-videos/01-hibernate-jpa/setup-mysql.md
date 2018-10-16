# MySQL

## docker container

- docker run
docker run --name mysql_local -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest
- Login into the container instance
docker exec -it mysql_local bash
- Login into MySQL
mysql -u root -p # password: root
- Create database and add user
show databases;
create database ifinances;
GRANT ALL PRIVILEGES ON ifinances.* TO 'infinite'@'%' IDENTIFIED BY 'skills' WITH GRANT OPTION;
use ifinances;

> Workbench

```
User: infinite
Password: skills

use ifinances;
TRUNCATE TABLE Person;
select * from Person;
```
 
## Docker Container

> Force delete all containers

docker rm -f $(docker ps -a -q)

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
