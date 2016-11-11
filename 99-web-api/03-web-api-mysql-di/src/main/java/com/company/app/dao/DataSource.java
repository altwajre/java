package com.company.app.dao;

import com.company.app.DataSourceConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {

    public static Connection createConnection(DataSourceConfig config){

        try {
            Class.forName(config.getDriverClass()).newInstance();
            Connection connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword());

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
