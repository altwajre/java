-- Start MySql Server: sudo /usr/local/mysql/bin/mysqld -u root
-- Stop MySql Server: sudo /usr/local/mysql/support-files/mysql.server stop

-- Workbench
-- SHOW DATABASES;

DROP DATABASE webapi;

CREATE DATABASE webapi;

-- CREATE USER 'webapiuser'@'localhost' IDENTIFIED BY'webapipassword';
-- SELECT * FROM mysql.user;

GRANT ALL ON webapi.* TO 'webapiuser'@'localhost';

USE webapi;

DROP TABLE IF EXISTS contact;

CREATE TABLE IF NOT EXISTS contact (
      id int(11) NOT NULL AUTO_INCREMENT,
      name varchar(255) NOT NULL,
      PRIMARY KEY (`id`)
      );

-- SHOW TABLES;

INSERT INTO contact VALUES (NULL, 'Tom'), (NULL, 'Dick'), (NULL, 'Harry');

SELECT id, name FROM webapi.contact;

DROP TABLE IF EXISTS car;

CREATE TABLE IF NOT EXISTS car (
      id int(11) NOT NULL AUTO_INCREMENT,
      make varchar(40) NOT NULL,
      PRIMARY KEY (`id`)
);

INSERT INTO car VALUES (NULL, 'Honda'), (NULL, 'BMW'), (NULL, 'Ford');

SELECT id, make FROM webapi.car;

DROP PROCEDURE IF EXISTS get_car_by_make;

DELIMITER //
CREATE PROCEDURE get_car_by_make(IN make VARCHAR(40))
BEGIN
  SELECT * FROM webapi.car WHERE car.make = make;
END //
DELIMITER ;

CALL get_car_by_make('bmw');
