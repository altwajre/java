# MySQL Installation

## docker container

> First time download docker image and docker run

docker run --name mysql_local -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest
 
> Docker run after image is downloaded

docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest

> Login into the container instance

docker ps
CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS                            PORTS                               NAMES
6c6225d60019        mysql/mysql-server:latest   "/entrypoint.sh my..."   6 seconds ago       Up 3 seconds (health: starting)   0.0.0.0:3306->3306/tcp, 33060/

docker exec -it 6c6225d60019 bash

> Login into MySQL

mysql -u root -p # password: root

show databases;

create database ifinances;

GRANT ALL PRIVILEGES ON ifinances.* TO 'infinite'@'%' IDENTIFIED BY 'skills' WITH GRANT OPTION;
 
> Workbench

User: infinite
Password: skills

use ifinances;
TRUNCATE TABLE Person;
select * from Person;
 
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

## MySQL Workbench

```
click "Create a new Schema" button, Name: ifinances, click "Apply" and "Finish"
click "Users and Privileges" on the left, click "Add Account", Login Name: infinite, Password: skills
click "Schema Privileges" tag, click "Add Entry...", click "Selected schema:" and select "ifinances", click "Select All", click "Apply"
```

### Setup New Connection

```
click "Home", click "Setup New Connection" button
Connection Name: ifinances  Username: infinite  Password: skills (Store in Keychain...)
Login to ifinances connection
Select database - use ifinances;
copy /source-code/Chapter 1/InfiniteFinancesSchema.sql to Query 1 window
click "Execute" button, click refresh, you will see schemas is built under ifinances
```
