# Common SQL

```
mysql -u root -p # password: root
 
show databases;
 
create database my_db;

GRANT ALL PRIVILEGES ON my_db.* TO 'user'@'%' IDENTIFIED BY 'password' WITH GRANT OPTION;

use my_db;
show tables;
CREATE TABLE `Person` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
)
describe Person;
show create table Person;
TRUNCATE TABLE Person;
select * from Person;
```
