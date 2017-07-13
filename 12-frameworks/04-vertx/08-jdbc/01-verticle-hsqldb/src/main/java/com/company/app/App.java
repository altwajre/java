package com.company.app;

import java.sql.*;

public class App
{
    public static void main( String[] args ) throws Exception {

        // Registering the HSQLDB JDBC driver
        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        //Creating the connection with HSQLDB
        Connection connection = DriverManager.getConnection(
            "jdbc:hsqldb:hsql://localhost/testdb",
            "SA",
            "");
        Statement statement = connection.createStatement();

//        dropTable(statement);

//        createTable(statement);
//        insertRecord(statement);
        selectAll(statement);

    }

    private static void selectAll(Statement statement) throws SQLException {

        System.out.println("#selectAll");
        String query = "SELECT * FROM Customers;";
        ResultSet result = statement.executeQuery(query);

        while(result.next()){
            System.out.println(
                result.getString("name")+" | "+
                result.getString("age"));
        }
    }

    private static void insertRecord(Statement statement) throws SQLException {

        System.out.println("#insertRecord");
        String query = "INSERT INTO Customers VALUES ('Tom', 28 );";
        int result = statement.executeUpdate(query);
        System.out.println("result=" + result);
        System.out.println("Insert successfully");
    }

    private static void dropTable(Statement statement) throws SQLException {

        System.out.println("#dropTable");
        String dropTable = "DROP TABLE IF EXISTS Customers";
        int result = statement.executeUpdate(dropTable);
        System.out.println("result=" + result);
        System.out.println("Table drop successfully");
    }

    private static void createTable(Statement statement) throws SQLException {

        System.out.println("#createTable");
        String createTable = "CREATE TABLE IF NOT EXISTS Customers (name VARCHAR (20), age INT);";
        int result = statement.executeUpdate(createTable);
        System.out.println("result=" + result);
        System.out.println("Table created successfully");
    }

}
