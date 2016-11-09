-- Start MySql Server: sudo /usr/local/mysql/bin/mysqld -u root
-- Stop MySql Server: sudo /usr/local/mysql/support-files/mysql.server stop

-- Workbench
-- SHOW DATABASES;

DROP DATABASE phonebook;

CREATE DATABASE phonebook;

-- CREATE USER 'phonebookuser'@'localhost' IDENTIFIED BY'phonebookpassword';
-- SELECT * FROM mysql.user;

GRANT ALL ON phonebook.* TO 'phonebookuser'@'localhost';

USE phonebook;

CREATE TABLE IF NOT EXISTS `contact` (
      `id` int(11) NOT NULL AUTO_INCREMENT,
      `name` varchar(255) NOT NULL,
      PRIMARY KEY (`id`)
      );

SHOW TABLES;

INSERT INTO `contact` VALUES (NULL, 'Tom'), (NULL, 'Dick'), (NULL, 'Harry');

SELECT id, name FROM phonebook.contact;
