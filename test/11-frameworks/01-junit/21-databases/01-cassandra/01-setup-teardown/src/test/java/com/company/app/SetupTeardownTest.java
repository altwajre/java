package com.company.app;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.junit.*;

/*
CQL:
-----------------------
DESCRIBE KEYSPACES;

CREATE KEYSPACE home_security WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

DESCRIBE KEYSPACE home_security;

DROP KEYSPACE home_security;

-----------------------
USE home_security;

CREATE TABLE IF NOT EXISTS activity (
home_id text,
datetime timestamp,
event text,
code_used text,
PRIMARY KEY (home_id, datetime)
);

DESCRIBE TABLE activity;

DROP TABLE IF EXISTS activity;

DESCRIBE TABLES;

 */
public class SetupTeardownTest {

  static Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1")
      .build();

  // create session on the "hotel" keyspace
  static Session session = cluster.connect("home_security");

  private static void createTable(Session session) {

    System.out.println("createTable");

    // Query
    String query = "CREATE TABLE IF NOT EXISTS activity (home_id text, "
        + "datetime timestamp, "
        + "event text, "
        + "code_used text, "
        + "PRIMARY KEY (home_id, datetime));";

    // Executing the query
    session.execute(query);
  }

  private static void dropTable(Session session) {

    System.out.println("dropTable");

    // Query
    String query = "DROP TABLE IF EXISTS activity;";

    // Executing the query
    session.execute(query);
  }

  @BeforeClass
  public static void setUpClass() {
    System.out.println("@BeforeClass setupClass");
    createTable(session);
  }

  @AfterClass
  public static void tearDownClass() {
    System.out.println("@AfterClass tearDownClass");
    dropTable(session);
  }

  @Before
  public void setUp() {
    System.out.println("@Before setup");
  }

  @After
  public void tearDown() {
    System.out.println("@After tearDown");
  }

  @Test
  public void test1() {
    System.out.println("@Test test1()");
  }

  @Test
  public void test2() {
    System.out.println("@Test test2()");
  }

}
