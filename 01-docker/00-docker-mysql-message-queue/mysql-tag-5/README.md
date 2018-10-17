# Work queues

https://www.rabbitmq.com/tutorials/tutorial-two-java.html

> rabbitmq

docker run -d --hostname myrabbit --name myrabbit -p 5672:5672 -p 15672:15672 rabbitmq:3-management

> mysql

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
Enter password: <- root

mysql> show databases;
create database ifinances;
GRANT ALL PRIVILEGES ON ifinances.* TO 'infinite'@'%' IDENTIFIED BY 'skills';
use ifinances;

> Workbench
User: infinite
Password: skills

run InfiniteFinancesSchema.sql in mysql workbench

## cleanup

- Force delete all containers
docker rm -f $(docker ps -a -q)
- Force delete all images
docker rmi -f $(docker images -q)

- run InfiniteFinancesSchema.sql in mysql workbench before running Apps

> Run Apps in intellij

- run database-receiver first
- run ui-sender second
