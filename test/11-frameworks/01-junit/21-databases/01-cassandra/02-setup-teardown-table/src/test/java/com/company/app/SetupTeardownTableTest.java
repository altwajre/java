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

public class SetupTeardownTableTest {

  @BeforeClass
  public static void setUpClass() {
    System.out.println("@BeforeClass setupClass");
    DBHelper.createTables();
  }

  @AfterClass
  public static void tearDownClass() {
    System.out.println("@AfterClass tearDownClass");
    DBHelper.dropTables();
    DBHelper.tearDown();
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
/*
output:
createTables
@Before setup
@Test test1()
@After tearDown
@Before setup
@Test test2()
@After tearDown
@AfterClass tearDownClass
dropTables
tearDown
 */
