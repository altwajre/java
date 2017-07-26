package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        String address = "cassandra-01"; // docker container
//        String address = "127.0.0.1"; // mac
        Cluster cluster = Cluster.builder().addContactPoint(address)
            .build();

        // create session
        Session session = cluster.connect();

        dropKeyspace(session);
        createKeyspace(session);

        connectKeyspace(session);

        dropTable(session);
        createTable(session);

        insertRecord(session);
        selectAll(session);

        session.close();
        cluster.close();

        System.out.println("#END");

    }

    public static void dropKeyspace(Session session) {

        System.out.println("#dropKeyspace");

        String query = "DROP KEYSPACE IF EXISTS testdb;";

        session.execute(query);
    }

    public static void createKeyspace(Session session) {

        System.out.println("#createKeyspace");

        String query = "CREATE KEYSPACE IF NOT EXISTS testdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};";

        session.execute(query);
    }

    private static void connectKeyspace(Session session) {

        System.out.println("#connectKeyspace");

        String query = "USE testdb;";

        session.execute(query);
    }

    private static void dropTable(Session session) {

        System.out.println("#dropTable");

        String query = "DROP TABLE IF EXISTS customer;";

        session.execute(query);
    }

    private static void createTable(Session session) {

        System.out.println("#createTable");

        String query = "CREATE TABLE IF NOT EXISTS customer ("
            + "id text PRIMARY KEY,"
            + "name text,"
            + "age text);";

        session.execute(query);
    }

    private static void insertRecord(Session session) {

        System.out.println("#insertRecord");

        String query = "INSERT INTO customer (id, name, age)"
            + "VALUES ('123', 'Tom', '28');";

        session.execute(query);
    }

    private static void selectAll(Session session) {

        System.out.println("#selectAll");

        String query = "SELECT * FROM customer;";

        ResultSet resultSet = session.execute(query);

//        for(Row row : resultSet){
//            System.out.println(row.getString("name").toString());
//        }

        MappingManager manager = new MappingManager(session);
        Mapper<Customer> mapper = manager.mapper(Customer.class);
        Result<Customer> customers = mapper.map(resultSet);
        List<Customer> all = customers.all();
        System.out.println("all: "+all);

        for(Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
/*
output:
#dropKeyspace
#createKeyspace
#connectKeyspace
#dropTable
#createTable
#insertRecord
#selectAll
Tom
#END
 */
