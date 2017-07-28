package io.vertx.starter;

import java.sql.*;

public class App {
  public static void main(String[] args) throws Exception {
    // Registering the HSQLDB JDBC driver
    Class.forName("org.hsqldb.jdbc.JDBCDriver");

    //Creating the connection with HSQLDB
    Connection connection = DriverManager.getConnection(
      "jdbc:hsqldb:hsql://localhost/wiki",
      "SA",
      "");
    Statement statement = connection.createStatement();

    selectAll(statement);

  }

  private static void selectAll(Statement statement) throws SQLException {

    System.out.println("#selectAll");
    String query = "SELECT * FROM Pages;";
    ResultSet result = statement.executeQuery(query);

    while(result.next()){
      System.out.println(
        result.getString("Name"));
    }
  }

}
/*
output:
#selectAll
foo
 */
