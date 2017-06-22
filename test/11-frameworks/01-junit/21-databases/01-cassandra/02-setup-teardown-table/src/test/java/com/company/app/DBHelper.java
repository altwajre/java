package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class DBHelper {

  static Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
      .build();

  // create session on the "home_security" keyspace
  static Session session = cluster.connect("home_security");

  public static void createTables() {

    System.out.println("createTables");

    // Query
    String query = "CREATE TABLE IF NOT EXISTS activity (home_id text, "
        + "datetime timestamp, "
        + "event text, "
        + "code_used text, "
        + "PRIMARY KEY (home_id, datetime));";

    // Executing the query
    session.execute(query);
  }

  public static void dropTables() {

    System.out.println("dropTables");

    // Query
    String query = "DROP TABLE IF EXISTS activity;";

    // Executing the query
    session.execute(query);
  }

  public static void tearDown() {

    System.out.println("tearDown");

    session.close();
    cluster.close();
  }

}
