DROP DATABASE IF EXISTS cookbook;
CREATE DATABASE cookbook;

USE cookbook;

CREATE USER 'cbuser'@'localhost' IDENTIFIED BY 'cbpass';
GRANT ALL ON cookbook.* TO 'cbuser'@'localhost'; -- Grant cbuser to cookbook database
SELECT user FROM mysql.user;  -- show users
