create database test;

CREATE TABLE Customers(
   id   INT              NOT NULL,
   name VARCHAR (20)     NOT NULL,
   age  INT              NOT NULL,
   PRIMARY KEY (id)
);

INSERT INTO Customers (id,name,age)
VALUES (1, 'Tom', 28 );

INSERT INTO Customers (id,name,age)
VALUES (2, 'Dick', 18 );

INSERT INTO Customers (id,name,age)
VALUES (3, 'Harry', 38 );

SELECT * FROM Customers;

DROP TABLE Customers;
