package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        String dbName = "oraclejbdc";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        Statement statement = connection.createStatement();

        // Create table
        String createTable =
                "create table " + dbName +
                        ".MyTable " +
                        "(ID integer NOT NULL, " +
                        "NAME varchar(40) NOT NULL, " +
                        "PRIMARY KEY (ID))";
        int createTableResult = statement.executeUpdate(createTable);
        System.out.println("create table: "+createTableResult);

        // Insert records
        int insertResult = statement.executeUpdate(
                "insert into " + dbName +
                        ".MyTable " +
                        "values(1, 'Tom')"
        );
        System.out.println("insert record: " + insertResult);

        statement.executeUpdate(
                "insert into " + dbName +
                        ".MyTable " +
                        "values(2, 'Dick')"
        );

        // Select
        ResultSet resultSet = statement.executeQuery("select * from MyTable");

        while (resultSet.next()){
            System.out.println(resultSet.getString("Name"));
        }

        int dropTableResult = statement.executeUpdate("drop table MyTable");
        System.out.println("drop table: " + dropTableResult);

        statement.close();
        connection.close();
    }
}
/*
output:
0
1
Tom
Dick

NOTE: DROP "MyTable" table.
 */
