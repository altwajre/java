package com.company.app;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestngTest {
  @BeforeClass
  public void beforeClass(){
    System.out.println("Before class executed");
  }

  @DataProvider
  public Object[][] dataMethod(){
    return new String[][]{
        new String[]{"one"}, new String[]{"two"}
    };
  }

  @Test(dataProvider = "dataMethod")
  public void testMethod(String param){
    System.out.println("The parameter value is: " + param);
  }
}
/*
[TestNG] Running:
  /Users/whan/Library/Caches/IdeaIC2017.2/temp-testng-customsuite.xml
Before class executed
The parameter value is: one
The parameter value is: two

===============================================
Default Suite
Total tests run: 2, Failures: 0, Skips: 0
===============================================
 */
