package com.company.app.dao;

import com.company.app.DataSourceConfig;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class DataSource {

    public static Session createSession(DataSourceConfig config){
        String address = config.getAddress();
        Cluster cluster = Cluster.builder().addContactPoint(address).build();
        Session session = cluster.connect();
        return session;
    }

}
