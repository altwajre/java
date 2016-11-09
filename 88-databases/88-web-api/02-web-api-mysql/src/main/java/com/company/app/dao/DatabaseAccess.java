package com.company.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseAccess {
    private static Connection connection;
    protected DatabaseAccess(){

    }
    public static Connection getConnection(){
        if (connection == null){
            String url = "jdbc:mysql://localhost/webapi";
            String userName = "webapiuser";
            String password = "webapipassword";

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection(url, userName, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
