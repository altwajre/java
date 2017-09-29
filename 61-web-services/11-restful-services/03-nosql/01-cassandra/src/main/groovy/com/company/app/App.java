package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<EcommConfiguration> {

    public static void main(String... args) throws Exception {
        new App().run(args);
//        test1();

    }

    @Override
    public void initialize(Bootstrap<EcommConfiguration> bootstrap) {
        GuiceBundle<EcommConfiguration> guiceBundle = GuiceBundle.<EcommConfiguration>newBuilder()
                .addModule(new EcommModule())
                .setConfigClass(EcommConfiguration.class)
                .enableAutoConfig(getClass().getPackage().getName())
                .build();
        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(EcommConfiguration config, Environment env) throws Exception {

    }

    private static void test1() {
        Cluster cluster = Cluster.builder().addContactPoint("localhost").build();
        Session session = cluster.connect();
        // database (keyspace): home_security  table: home
        String queryString = "SELECT id, make, color FROM webapi.car;";
        ResultSet resultSet = session.execute(queryString);
        for(Row row : resultSet){
            int id = row.getInt("id");
            System.out.println(id);
            System.out.println(row.getString("make").toString());
        }
        session.close();
        cluster.close();
    }

}
