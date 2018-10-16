package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
https://www.tutorialspoint.com/hsqldb/hsqldb_delete_clause.htm
 */
public class App {
  public static void main(String[] args) throws Exception {

    // Registering the HSQLDB JDBC driver
    Class.forName("org.hsqldb.jdbc.JDBCDriver");

    //Creating the connection with HSQLDB
    Connection connection = DriverManager.getConnection(
        "jdbc:hsqldb:hsql://localhost/testdb",
        "SA",
        "");
    Statement statement = connection.createStatement();

    String query = "DELETE FROM tutorials_tbl   WHERE id=105";

    int result = statement.executeUpdate(query);
    connection.commit();

    System.out.println("result=" + result);
    System.out.println(result + " Rows effected");

  }
}
/*
output:
result=1
1 Rows effected
 */
