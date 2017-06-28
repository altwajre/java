# hsqldb

http://hsqldb.org/

http://hsqldb.org/doc/2.0/guide/index.html

## Download and running database

http://www.programmingforfuture.com/2010/06/using-hypersql-hsqldb.html

> download

http://hsqldb.org/

> location

/Desktop/hsqldb-2.4.0/hsqldb

### running database

> prepare server.properties

```
$ cat hsqldb/server.properties 
server.database.0=file:hsqldb/whan
server.dbname.0=testdb
```

> connection URL

```
jdbc:hsqldb:file:testdb
```

> create database

```
$ java -classpath lib/hsqldb.jar org.hsqldb.server.Server
```

> start database

```
$ java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/whan --dbname.0 testdb
```

> database manager

```
$ java -cp lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
```

> sql


CREATE TABLE Customers(name VARCHAR (20), age INT);

INSERT INTO Customers (name,age) VALUES ('Tom', 28 );

SELECT * FROM Customers;



```
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
```
