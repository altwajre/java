package com.company.app;

import java.sql.*;

public class App
{
    static void tryQuery(Connection conn){
        Statement s = null;
        try {
            // issue a simple query
            s = conn.createStatement();
            s.execute("USE cookbook");
            s.close();

            // print any accumulated warnings
            SQLWarning w = conn.getWarnings();
            while (w != null){
                System.err.println("SQLWarning: " + w.getMessage());
                System.err.println("SQLState: " + w.getSQLState());
                System.err.println("Vendor code: " + w.getErrorCode());
                w = w.getNextWarning();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    static void printException(SQLException e){
        // print general message, plus any database-specific message
        System.err.println("SQLException: " + e.getMessage());
        System.err.println("SQLState: " + e.getSQLState());
        System.err.println("Vendor code: ");
    }
    public static void main( String[] args )
    {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/cookbook";
        String userName = "baduser";
        String password = "badpass";

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected");
            tryQuery(conn); // issue a query
        } catch (Exception e) {
            System.err.println("Cannot connect to server");
            System.err.println(e);
            if(e instanceof SQLException){ // JDBC-specific exception
                // e must be cast from Exception to SQLException to
                // access the SQLException-specific methods
                printException((SQLException)e);
            }
        }
        finally{
            if(conn != null){
                try {
                    conn.close();
                    System.out.println("Disconnected");
                } catch (SQLException e) {
                    printException(e);
                }
            }
        }
    }
}
/*
output:
Cannot connect to server
java.sql.SQLException: Access denied for user 'baduser'@'localhost' (using password: YES)
SQLException: Access denied for user 'baduser'@'localhost' (using password: YES)
SQLState: 28000
Vendor code:
 */