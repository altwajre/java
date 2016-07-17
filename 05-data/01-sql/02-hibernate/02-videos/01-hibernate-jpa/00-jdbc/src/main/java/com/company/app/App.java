package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App
{
    public static void main( String[] args ) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String sqlQuery = "SELECT * FROM finances_user";
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ifinances", "infinite", "skills");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while(resultSet.next()){
            System.out.println(resultSet.getString("FIRST_NAME"));
        }

        statement.close();
        connection.close();
    }
}
/*
output:
Kevin
Tom
Tom
Joe
Tom
 */