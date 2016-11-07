package com.company.app;

import java.sql.*;

public class App
{
    public static void main( String[] args )
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

            Statement s = conn.createStatement();
            s.executeQuery("SELECT name, birth, foods FROM profile");
            ResultSet rs = s.getResultSet();
            ResultSetMetaData md = rs.getMetaData();
            int ncols = md.getColumnCount();
            while(rs.next()){  // loop through rows of result set
                for(int i = 0; i < ncols; i++){ // loop through columns
                    String val = rs.getString(i + 1);
                    if(i > 0){
                        System.out.print(", ");
                    }
                    if(rs.wasNull()){
                        System.out.print("NULL"); // identify null values in result sets
                    }
                    else{
                        System.out.print(val);
                    }
                }
                System.out.println();
            }
            rs.close();
            s.close();
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
Language   | NULL-detection value or method
Java JDBC  | wasNull() method

output:
Connected
Sybil, 1970-04-13, lutefisk,fadge,pizza
Nancy, 1969-09-30, burrito,curry,eggroll
Ralph, 1973-11-02, eggroll,pizza
Lothair, 1963-07-04, burrito,curry
Henry, 1965-02-14, curry,fadge
Aaron, 1968-09-17, lutefisk,fadge
Joanna, 1952-08-20, lutefisk,fadge
Stephen, 1960-05-01, burrito,pizza
Disconnected
 */
