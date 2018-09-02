package com.company.app.testng;

import org.testng.annotations.DataProvider;

public abstract class DataDrivenBase {
  @DataProvider
  public Object[][] dataMethod(){
    return new String[][]{
        new String[]{"one"},
        new String[]{"two"},
        new String[]{"three"}
    };
  }
}
