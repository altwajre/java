package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App
{
    public static void main( String[] args ) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        String sqlQuery = "SELECT * FROM SUPPLIERS";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while(resultSet.next()){
            System.out.println(resultSet.getString("SUP_NAME"));
        }

        statement.close();
        connection.close();
    }
}
/*
output:
Superior Coffee
Acme, Inc.
The High Ground
Restaurant Supplies, Inc.
Professional Kitchen
 */
