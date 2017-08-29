package com.company.app;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Closeable;
import java.io.IOException;

/*
https://github.com/junit-team/junit4/wiki/Test-fixtures
 */

class ExpensiveManagedResource implements Closeable {
  @Override
  public void close() throws IOException {

  }
}

class ManagedResource implements Closeable {
  @Override
  public void close() throws IOException {

  }
}

public class FixturesTest {

  private static ExpensiveManagedResource expensiveManagedResource;
  private ManagedResource managedResource;

  @BeforeClass
  public static void setUpClass() {
    System.out.println("@BeforeClass setupClass");
    expensiveManagedResource = new ExpensiveManagedResource();
  }

  @AfterClass
  public static void tearDownClass() throws IOException {
    System.out.println("@AfterClass tearDownClass");
    expensiveManagedResource.close();
    expensiveManagedResource = null;
  }

  @Before
  public void setUp() {
    System.out.println("@Before setup");
    this.managedResource = new ManagedResource();
  }

  @After
  public void tearDown() throws IOException {
    System.out.println("@After tearDown");
    this.managedResource.close();
    this.managedResource = null;
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
@BeforeClass setupClass
@Before setup
@Test test1()
@After tearDown
@Before setup
@Test test2()
@After tearDown
@AfterClass tearDownClass
 */
