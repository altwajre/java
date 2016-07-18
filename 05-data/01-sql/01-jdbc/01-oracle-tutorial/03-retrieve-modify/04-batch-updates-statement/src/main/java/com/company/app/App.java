package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

// Using Statement Objects for Batch Updates
public class App
{
    public static void main( String[] args ) throws Exception {
        String dbName = "oraclejbdc";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        connection.setAutoCommit(false);

        Statement statement = connection.createStatement();

        statement.addBatch("INSERT INTO COFFEES VALUES('Amaretto', 49, 1.99, 0, 0)");
        statement.addBatch("INSERT INTO COFFEES VALUES('Hazelnut', 49, 1.99, 0, 0)");
        statement.addBatch("INSERT INTO COFFEES VALUES('Amaretto_decaf', 49, 1.99, 0, 0)");
        statement.addBatch("INSERT INTO COFFEES VALUES('Hazelnut_decaf', 49, 1.99, 0, 0)");

        int[] updateCounts = statement.executeBatch();
        System.out.println(Arrays.toString(updateCounts));
        connection.commit();

        String query = "SELECT * FROM " + dbName + ".COFFEES";

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
        connection.setAutoCommit(true);
        connection.close();
    }
}
/*
output:
[1, 1, 1, 1]
Amarette	49	1.99	0	0
Amaretto_decaf	49	1.99	0	0
Colombian	101	6.39	0	0
Colombian_Decaf	101	8.99	0	0
Espresso	150	9.99	0	0
French_Roast	49	8.99	0	0
French_Roast_Decaf	49	9.99	0	0
Hazelnut	49	1.99	0	0
Hazelnut_decaf	49	1.99	0	0

NOTE: run below in workbench after finished test run
DELETE FROM COFFEES WHERE PRICE = 1.99;
 */
