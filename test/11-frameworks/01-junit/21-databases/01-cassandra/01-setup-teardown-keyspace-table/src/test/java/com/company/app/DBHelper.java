package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class DBHelper {

  static Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
      .build();

  // create session
  static Session session = cluster.connect();

  public static void createKeyspace() {

    System.out.println("createKeyspace");

    String query = "CREATE KEYSPACE IF NOT EXISTS home_security WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};";

    session.execute(query);
  }

  public static void dropKeyspace() {

    System.out.println("dropKeyspace");

    String query = "DROP KEYSPACE IF EXISTS home_security;";

    session.execute(query);
  }

  public static void connectKeyspace() {

    System.out.println("connectKeyspace");

    // Query
    String query = "USE home_security";

    // Executing the query
    session.execute(query);
  }

  public static void createTables() {

    System.out.println("createTables");

    // Query
    String query = "CREATE TABLE IF NOT EXISTS activity (home_id text, datetime timestamp, event text, code_used text, PRIMARY KEY (home_id, datetime));";

    // Executing the query
    session.execute(query);

    query = "CREATE TABLE IF NOT EXISTS home (home_id text, address text, city text, state text, zip text, contact_name text, phone text, alt_phone text, phone_password text, email text, main_code text, guest_code text, PRIMARY KEY (home_id));";

    // Executing the query
    session.execute(query);
  }

  public static void dropTables() {

    System.out.println("dropTables");

    // Query
    String query = "DROP TABLE IF EXISTS activity;";

    // Executing the query
    session.execute(query);

    query = "DROP TABLE IF EXISTS home;";

    // Executing the query
    session.execute(query);
  }

  public static void tearDown() {

    System.out.println("tearDown");

    session.close();
    cluster.close();
  }

}
