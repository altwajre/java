package com.company.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Updating Rows in ResultSet Objects
public class App
{
    public static void main( String[] args ) throws Exception {
        String dbName = "oraclejbdc";
        float percentage = 0.8f;

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/oraclejbdc", "root", "abc");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String query = "SELECT * FROM " + dbName + ".COFFEES";
        ResultSet updateResultSet = statement.executeQuery(query);

        while (updateResultSet.next()){
            float price = updateResultSet.getFloat("PRICE");
            if(price == 7.99f){
                System.out.println("update price: " + price);
                updateResultSet.updateFloat("PRICE", price * percentage);
                updateResultSet.updateRow();
            }
        }

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
update price: 7.99
Colombian	101	6.39	0	0
Colombian_Decaf	101	8.99	0	0
Espresso	150	9.99	0	0
French_Roast	49	8.99	0	0
French_Roast_Decaf	49	9.99	0	0

NOTE: run following SQL statement in Workbench after finish test run
UPDATE COFFEES SET PRICE = 7.99 WHERE COF_NAME = 'Colombian';
 */