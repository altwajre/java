package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App
{
    public static void main( String[] args ) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String dbName = "oraclejbdc";
        String createTable =
                "create table " + dbName +
                ".MyTable " +
                "(ID integer NOT NULL, " +
                "NAME varchar(40) NOT NULL, " +
                "PRIMARY KEY (ID))";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        Statement statement = connection.createStatement();
        int result = statement.executeUpdate(createTable);
        System.out.println(result);

        statement.close();
        connection.close();
    }
}
/*
output:
0

NOTE: DROP "MyTable" table.
 */
