package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

// Committing Transactions
public class App
{
    public static void main( String[] args )throws Exception {
        String dbName = "oraclejbdc";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        connection.setAutoCommit(false);

        PreparedStatement updateSales = connection.prepareStatement(
                "update " + dbName + ".COFFEES set SALES = ? where COF_NAME = ?");

        PreparedStatement updateTotal = connection.prepareStatement(
                "update " + dbName + ".COFFEES set TOTAL = TOTAL + ? where COF_NAME = ?");

        updateSales.setInt(1, 8);
        updateSales.setString(2, "Colombian");
        updateSales.executeUpdate();

        updateTotal.setInt(1, 2);
        updateTotal.setString(2, "Colombian");
        updateTotal.executeUpdate();

        connection.commit();

        String query = "SELECT * FROM " + dbName + ".COFFEES where COF_NAME = 'Colombian'";
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
        statement.close();

        updateSales.close();
        updateTotal.close();
        connection.setAutoCommit(true);
        connection.close();
    }
}
/*
output:
Colombian	101	7.99	8	2

UPDATE COFFEES SET SALES = 0 WHERE COF_NAME = 'Colombian';
UPDATE COFFEES SET TOTAL = 0 WHERE COF_NAME = 'Colombian';
 */
