package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.stream.Collectors;

public class App {

  private static final String CREATE_TABLE = "CREATE TABLE tutorials_tbl (id INT NOT NULL, title VARCHAR(50) NOT NULL, author VARCHAR(20) NOT NULL, submission_date DATE, PRIMARY KEY (id));";
  private static final String SELECT_ALL = "SELECT id, title, author FROM tutorials_tbl";

  public static void main(String[] args) {

    Vertx vertx = Vertx
        .vertx();

    JDBCClient dbClient = JDBCClient.createShared(vertx, new JsonObject()
        .put("url", "jdbc:hsqldb:hsql://localhost/testdb")
//        .put("user", "SA")
//        .put("password", "")
        .put("driver_class", "org.hsqldb.jdbcDriver"));

    dbClient.getConnection(ar -> {
      if(ar.failed()) {
        System.out.println("fail");
      }
      else {
        System.out.println("pass");

        SQLConnection connection = ar.result();
        connection.query(SELECT_ALL, res -> {
          connection.close();
          if(res.succeeded()) {
            List<String> list = res.result()
                .getResults()
                .stream()
                .map(json -> json.getString(1))
                .collect(Collectors.toList());

            System.out.println(list);
          }
        });
      }
    });


//    vertx
//        .createHttpServer()
//        .requestHandler(req -> req
//            .response()
//            .end("Hello World from Vert.x!"))
//        .listen(8080);

  }
}
/*
output:
pass
[Learn PHP, C and Data Structures, Learn MySQL, Learn Excell, Learn JDB]
 */
