package com.company.app;

import java.sql.*;

public class App
{
    public static void main( String[] args ) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:hsqldb:file:foodb", "SA", "");

        String createTable = "CREATE TABLE Customers(name VARCHAR (20), age INT);";
        Statement statement = connection.createStatement();

        statement.executeUpdate(createTable);

        statement.executeUpdate("INSERT INTO Customers (name,age) VALUES ('Tom', 28 );");

        ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers;");

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
}
/*
output:
Tom
 */
