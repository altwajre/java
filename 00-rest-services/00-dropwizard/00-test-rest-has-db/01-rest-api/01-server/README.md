# server

## mysql

docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)

docker run --name mysql \
-e MYSQL_ROOT_PASSWORD=test \
-e MYSQL_DATABASE=phonebook \
-e MYSQL_USER=test \
-e MYSQL_PASSWORD=test \
-p 3306:3306 \
-d mysql

docker exec -it mysql bash
mysql -u root -p
Enter password: test
mysql> show databases;
mysql> GRANT ALL PRIVILEGES ON test.* TO 'test'@'%' IDENTIFIED BY 'test';
mysql> USE phonebook;

CREATE TABLE IF NOT EXISTS `contact` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `firstName` varchar(255) NOT NULL,
      `lastName` varchar(255) NOT NULL,
      `phone` varchar(30) NOT NULL,
      PRIMARY KEY (`id`)
      ) 
      ENGINE=InnoDB 
      DEFAULT CHARSET=utf8 
      AUTO_INCREMENT=1;

mysql> INSERT INTO `contact` VALUES (NULL, 'John', 'Doe', '+123456789'), (NULL, 'Jane', 'Doe', '+987654321');
mysql> select * from phonebook.contact;

## Server

cd 01-server

### Intellij

App.main()

### Make package

mvn package or mvn clean install

### Run the application

java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml

## Test

curl -X POST http://localhost:8080/contact -H 'content-type: application/json' -d '{"firstName": "Tom", "lastName": "Lee", "phone": "12345"}'
curl -X GET http://localhost:8080/contact/all

