package com.company.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

// https://github.com/junit-team/junit4/wiki/parameterized-tests

@RunWith(Parameterized.class)
public class JunitTest {

  @Parameterized.Parameter
  public String data;

  @Parameterized.Parameters
  public static Iterable<Object[]> data(){
    return Arrays.asList(new Object[][]{
        new String[]{"one"}, new String[]{"two"}
    });
  }

  @Test
  public void testMethod(){
    System.out.println("The parameter value is: " + data);
  }
}
