# docker mysql

## first time when no local image

> Create a MySQL server in a local docker container and setting the password to "root"

docker run --name mysql_local -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest

> Login to the instance 

docker exec -it "mysql_local" bash

## when you have local image

> Create a MySQL server in a local docker container and setting the password to "root"

docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server:latest
 
> Get container Id and Login to the instance 

docker ps
CONTAINER ID        IMAGE                       COMMAND                  CREATED             STATUS                            PORTS                               NAMES
6c6225d60019        mysql/mysql-server:latest   "/entrypoint.sh my..."   6 seconds ago       Up 3 seconds (health: starting)   0.0.0.0:3306->3306/tcp, 33060/

docker exec -it 6c6225d60019 bash # login by using container Id

## Login to MySQL client, create database, add user

mysql -u root -p # Your password is root if you copy/pasted the exact same command above

mysql> create database test_db;

mysql> GRANT ALL PRIVILEGES ON test_db.* TO 'test'@'%' IDENTIFIED BY 'test' WITH GRANT OPTION; # grant access to the user from any host
