package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Inserting Rows in ResultSet Objects
public class App
{
    public static void main( String[] args ) throws Exception {
        String dbName = "oraclejbdc";

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM " + dbName + ".COFFEES";
        ResultSet updateResultSet = statement.executeQuery(query);


        updateResultSet.moveToInsertRow();
        updateResultSet.updateString("COF_NAME", "Amaretto");
        updateResultSet.updateInt("SUP_ID", 49);
        updateResultSet.updateFloat("PRICE", 1.99f);
        updateResultSet.updateInt("SALES", 0);
        updateResultSet.updateInt("TOTAL", 0);
        updateResultSet.insertRow();
        updateResultSet.beforeFirst();

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
        connection.close();
    }
}
/*
output:
Amaretto	49	1.99	0	0
Colombian	101	7.99	0	0
Colombian_Decaf	101	8.99	0	0
Espresso	150	9.99	0	0
French_Roast	49	8.99	0	0
French_Roast_Decaf	49	9.99	0

NOTE: run below in workbench after finished test run
DELETE FROM COFFEES WHERE PRICE = 1.99;
 */
