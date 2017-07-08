package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;

/*
https://www.tutorialspoint.com/hsqldb/hsqldb_connect.htm
 */
public class App {
  public static void main(String[] args) throws Exception {

    // Registering the HSQLDB JDBC driver
    Class.forName("org.hsqldb.jdbc.JDBCDriver");

    //Creating the connection with HSQLDB
    Connection connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
    if (connection != null) {
      System.out.println("Connection created successfully");
    } else {
      System.out.println("Problem with creating connection");
    }
  }
}
/*
output:
Connection created successfully
 */
