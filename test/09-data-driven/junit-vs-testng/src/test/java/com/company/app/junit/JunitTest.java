package com.company.app.junit;

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
        new String[]{"one"},
        new String[]{"two"},
        new String[]{"three"}
    });
  }

  @Test
  public void test1(){
    System.out.println("test1: The parameter value is: " + data);
  }

  @Test
  public void test2(){
    System.out.println("test2: The parameter value is: " + data);
  }
}
/*
test1: The parameter value is: one
test2: The parameter value is: one
test1: The parameter value is: two
test2: The parameter value is: two
 */
