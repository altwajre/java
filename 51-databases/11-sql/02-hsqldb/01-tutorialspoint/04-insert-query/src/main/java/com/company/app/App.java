package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/*
https://www.tutorialspoint.com/hsqldb/hsqldb_insert_query.htm
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
    int result = 0;

    String query = "INSERT INTO tutorials_tbl VALUES (100,'Learn PHP', 'John Poul', NOW());";
    query += "INSERT INTO tutorials_tbl VALUES (101,'Learn C', 'Yaswanth', NOW());";
    query += "INSERT INTO tutorials_tbl VALUES (102,'Learn MySQL', 'Abdul S', NOW());";
    query += "INSERT INTO tutorials_tbl VALUES (103,'Learn Excell', 'Bavya kanna', NOW());";
    query += "INSERT INTO tutorials_tbl VALUES (104,'Learn JDB', 'Ajith kumar', NOW());";
    query += "INSERT INTO tutorials_tbl VALUES (105,'Learn Junit', 'Sathya Murthi', NOW());";

    result = statement.executeUpdate(query);
    connection.commit();

    System.out.println("result=" + result);
    System.out.println("Row inserted successfully");

  }
}
/*
output:
result=1
Row inserted successfully
 */
