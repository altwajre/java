package com.company.app;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

/*
The ErrorCollector Rule allows execution of a test to continue after the first problem is found.
(for example, to collect all the incorrect rows in a table, and report them all at once)
 */
public class C_ErrorCollectorTest {

  @Rule
  public final ErrorCollector collector = new ErrorCollector();

  @Test
  public void testCollectingErrors() {
    collector.addError(new Throwable("first thing went wrong"));
    collector.addError(new Throwable("second thing went wrong"));
  }
}
/*
output:
java.lang.Throwable: first thing went wrong

...

java.lang.Throwable: second thing went wrong
 */
