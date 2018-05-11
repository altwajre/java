package com.company.app;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

  @BeforeClass
  public void beforeClass() {
    System.out.println("Before class executed");
  }

  @DataProvider
  public Object[][] dataMethod() {
    return new String[][]{
        new String[]{"one"}, new String[]{"two"}
    };
  }

  @Test(dataProvider = "dataMethod")
  public void testMethod(String param) {
    System.out.println("The parameter value is: " + param);
  }

}
