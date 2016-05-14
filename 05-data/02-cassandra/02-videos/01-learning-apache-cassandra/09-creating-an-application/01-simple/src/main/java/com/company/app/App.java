package com.company.app;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;

// NOTE: ignore "SLF4J error"
public class App
{
    public static void main( String[] args )
    {
        queryString();

        queryBuilder();
    }

    private static void queryBuilder() {
        System.out.println("#queryBuilder");
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect();
        Select selectStatement = QueryBuilder.select().column("home_id").column("address")
                .column("phone").column("city").column("email")
                .from("home_security", "home"); // database (keyspace): home_security  table: home
        ResultSet resultSet = session.execute(selectStatement);
        for(Row row : resultSet){
            System.out.println(row.getString("address").toString());
        }
        session.close();
        cluster.close();
    }

    private static void queryString() {
        System.out.println("#queryString");
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect();
        // database (keyspace): home_security  table: home
        String queryString = "select * from home_security.home;";
        ResultSet resultSet = session.execute(queryString);
        for(Row row : resultSet){
            System.out.println(row.getString("address").toString());
        }
        session.close();
        cluster.close();
    }
}
