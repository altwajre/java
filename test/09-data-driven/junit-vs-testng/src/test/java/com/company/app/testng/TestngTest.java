package com.company.app.testng;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestngTest extends DataDrivenBase {
  @BeforeSuite
  public static void beforeSuite() {
    System.out.println("beforeSuite executed");
  }

  @Test(dataProvider = "dataMethod")
  public void test1(String param){
    System.out.println("test1: The parameter value is: " + param);
  }

  @Test(dataProvider = "dataMethod")
  public void test2(String param){
    System.out.println("test2: The parameter value is: " + param);
  }

}
/*
[TestNG] Running:
  /Users/whan/Library/Caches/IntelliJIdea2018.1/temp-testng-customsuite.xml
beforeSuite executed
test1: The parameter value is: one
test1: The parameter value is: two
test1: The parameter value is: three
test2: The parameter value is: one
test2: The parameter value is: two
test2: The parameter value is: three

===============================================
Default Suite
Total tests run: 6, Failures: 0, Skips: 0
===============================================
 */
