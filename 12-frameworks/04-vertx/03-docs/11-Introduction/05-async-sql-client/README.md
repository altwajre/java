# Async SQL Client

http://vertx.io/blog/using-the-asynchronous-sql-client/

> pom.xml

add following dependencies

```
    <dependency>
      <groupId>io.vertx</groupId>
      <artifactId>vertx-jdbc-client</artifactId>
      <version>3.4.2</version>
    </dependency>
    <dependency>
      <groupId>org.hsqldb</groupId>
      <artifactId>hsqldb</artifactId>
      <version>2.3.3</version>
    </dependency>
```

> Initializing the JDBC client

```
// Create a JDBC client
jdbc = JDBCClient.createShared(vertx, config());

or with dataSourceName

jdbc = JDBCClient.createShared(vertx, config(), "My-Whisky-Collection");
```

java -jar target/async-sql-client-1.0-SNAPSHOT-fat.jar -conf src/main/conf/conf.json
