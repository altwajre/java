package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class App {

    public static void main(String... args){
        Cluster cluster = Cluster.builder()
                .addContactPoint("127.0.0.1")
                .build();

        Session session = cluster.connect("hotel");

        ResultSet resultSet = session.execute("SELECT * FROM hotel.hotels");
        for(Row row : resultSet){
            System.out.println(row.getString("name").toString());
        }

        session.close();
        cluster.close();

    }
}
