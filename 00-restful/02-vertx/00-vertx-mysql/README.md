# local vertx mysql create

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
mysql> GRANT ALL PRIVILEGES ON test.* TO 'test'@'%' IDENTIFIED BY 'test';
mysql> use test;
- after curl post
mysql> SELECT * FROM Customer;

## java -jar on local machine

> command line

mvn clean package
java -jar target/vertx-mysql-1.0-SNAPSHOT.jar

> intellij

App.main()

## test

curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"name": "Tom", "age": 18}'
curl http://localhost:8080/api/customers/1
curl http://localhost:8080/api/customers
