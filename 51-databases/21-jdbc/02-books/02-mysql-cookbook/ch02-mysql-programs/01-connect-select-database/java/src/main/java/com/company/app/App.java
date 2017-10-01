package com.company.app;

import java.sql.*;

public class App
{
    public static void main (String[] args)
    {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/cookbook";
        String userName = "cbuser";
        String password = "cbpass";

        try
        {
            Class.forName ("com.mysql.jdbc.Driver"); // run Driver static block code.
            conn = DriverManager.getConnection (url, userName, password);
            System.out.println ("Connected");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.err.println ("Cannot connect to server");
            System.exit (1);
        }
        if (conn != null)
        {
            try
            {
                conn.close ();
                System.out.println ("Disconnected");
            }
            catch (Exception e) { /* ignore close errors */ }
        }
    }
}
/*
output:
Connected
Disconnected
 */
