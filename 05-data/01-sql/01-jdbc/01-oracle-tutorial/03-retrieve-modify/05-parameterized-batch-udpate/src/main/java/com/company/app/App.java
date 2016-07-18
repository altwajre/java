package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

// Performing Parameterized Batch Update
public class App
{
    public static void main( String[] args ) throws Exception {
        String dbName = "oraclejbdc";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO COFFEES VALUES(?, ?, ?, ?, ?)");

        preparedStatement.setString(1, "Amaretto");
        preparedStatement.setInt(2, 49);
        preparedStatement.setFloat(3, 1.99f);
        preparedStatement.setInt(4, 0);
        preparedStatement.setInt(5, 0);
        preparedStatement.addBatch();

        preparedStatement.setString(1, "Hazelnut");
        preparedStatement.setInt(2, 49);
        preparedStatement.setFloat(3, 1.99f);
        preparedStatement.setInt(4, 0);
        preparedStatement.setInt(5, 0);
        preparedStatement.addBatch();

        int[] updateCounts = preparedStatement.executeBatch();
        System.out.println(Arrays.toString(updateCounts));
        connection.commit();

        String query = "SELECT * FROM " + dbName + ".COFFEES";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()){
            String coffeeName = resultSet.getString(1);
            int supplierID = resultSet.getInt(2);
            float price = resultSet.getFloat(3);
            int sales = resultSet.getInt(4);
            int total = resultSet.getInt(5);
            System.out.println(coffeeName + "\t" + supplierID +
                    "\t" + price + "\t" + sales +
                    "\t" + total);
        }

        preparedStatement.close();
        statement.close();
        connection.setAutoCommit(true);
        connection.close();
    }
}
/*
output:
[1, 1]
Amaretto	49	1.99	0	0
Colombian	101	7.99	0	0
Colombian_Decaf	101	8.99	0	0
Espresso	150	9.99	0	0
French_Roast	49	8.99	0	0
French_Roast_Decaf	49	9.99	0	0
Hazelnut	49	1.99	0	0

NOTE: run below in workbench after finished test run
DELETE FROM COFFEES WHERE PRICE = 1.99;
 */
