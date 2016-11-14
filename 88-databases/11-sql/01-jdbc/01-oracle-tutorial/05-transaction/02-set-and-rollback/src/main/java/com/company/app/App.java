package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

// Setting and Rolling Back to Savepoints
public class App
{
    public static void main( String[] args )throws Exception {

        String coffeeName = "Colombian";
        float priceModifier = 10f;
        float maximumPrice = 20.99f;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        connection.setAutoCommit(false);

        Savepoint beginning = connection.setSavepoint();

        Statement getPrice = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        Statement updatePrice = connection.createStatement();

        String query = "SELECT COF_NAME, PRICE FROM COFFEES WHERE COF_NAME = '" + coffeeName + "'";
        ResultSet resultSet = getPrice.executeQuery(query);
        resultSet.first();
        float oldPrice = resultSet.getFloat("PRICE");
        float newPrice = oldPrice + (oldPrice * priceModifier);

        System.out.println("Old price of " + coffeeName + " is " + oldPrice);

        System.out.println("New price of " + coffeeName + " is " + newPrice);

        System.out.println("Performing update...");

        updatePrice.executeUpdate("UPDATE COFFEES SET PRICE = " + newPrice + " WHERE COF_NAME = '" + coffeeName + "'");

        System.out.println("\nCOFFEES table after update:");

        viewTable(connection);

        if(newPrice > maximumPrice) {
            System.out.println("\nThe new price, " + newPrice + ", is greater than the maximum price, "
                    + maximumPrice + ". Rolling back the transaction...");

            connection.rollback(beginning);

            System.out.println("\nCOFFEES table after rollback:");

            viewTable(connection);
        }

        connection.commit();

        connection.setAutoCommit(true);
        connection.close();
    }

    static void viewTable(Connection connection) throws SQLException {
        String query = "SELECT * FROM COFFEES where COF_NAME = 'Colombian'";
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
    }
}
/*
output:
Old price of Colombian is 7.99
New price of Colombian is 87.88999
Performing update...

COFFEES table after update:
Colombian	101	87.89	0	0

The new price, 87.88999, is greater than the maximum price, 20.99. Rolling back the transaction...

COFFEES table after rollback:
Colombian	101	7.99	0	0
 */
