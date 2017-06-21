package com.company.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/*
Using @Parameter for Field injection for public field
 */
@RunWith(Parameterized.class)
public class ParameterizedFieldInjectionTest {
  @Parameterized.Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {
        {0, 0}, {1, 1}, {2, 1}, {3, 2}, {4, 3}, {5, 5}, {6, 8}
    });
  }

  @Parameterized.Parameter
  public int fInput;

  @Parameterized.Parameter(1)
  public int fExpected;

  @Test
  public void test() {
    assertEquals(fExpected, Fibonacci.compute(fInput));
  }

}
