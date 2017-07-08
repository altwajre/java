# HSQLDB - Installation

https://www.tutorialspoint.com/hsqldb/hsqldb_installation.htm

> download

http://hsqldb.org/

> location

/Desktop/hsqldb-2.4.0/hsqldb

## running database

> prepare server.properties

```
$ cat hsqldb/server.properties 
server.database.0 = file:hsqldb/demodb
server.dbname.0 = testdb
```

> create a default database

```
$ java -classpath lib/hsqldb.jar org.hsqldb.server.Server
```

> start database

```
$ java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/demodb --dbname.0 testdb
```

> database manager

```
$ java -cp lib/hsqldb.jar org.hsqldb.util.DatabaseManagerSwing
```

URL: jdbc:hsqldb:hsql:localhost/testdb
