package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
https://www.tutorialspoint.com/hsqldb/hsqldb_create_table.htm

https://www.tutorialspoint.com/hsqldb/hsqldb_drop_table.htm
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

    createTable(statement);

//        dropTable(statement);

  }

  private static void dropTable(Statement statement) throws SQLException {

    System.out.println("#dropTable");
    String dropTable = "DROP TABLE tutorials_tbl";
    int result = statement.executeUpdate(dropTable);
    System.out.println("result=" + result);
    System.out.println("Table drop successfully");
  }

  private static void createTable(Statement statement) throws SQLException {

    System.out.println("#createTable");
    String createTable = "CREATE TABLE tutorials_tbl (id INT NOT NULL, title VARCHAR(50) NOT NULL, author VARCHAR(20) NOT NULL, submission_date DATE, PRIMARY KEY (id));";
    int result = statement.executeUpdate(createTable);
    System.out.println("result=" + result);
    System.out.println("Table created successfully");
  }
/*
output:
#createTable
result=0
Table created successfully
 */
}
