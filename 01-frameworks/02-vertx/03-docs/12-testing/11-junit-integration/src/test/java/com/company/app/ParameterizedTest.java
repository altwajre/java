package com.company.app;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunnerWithParametersFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
@Parameterized.UseParametersRunnerFactory(VertxUnitRunnerWithParametersFactory.class)
public class ParameterizedTest {

  private Vertx vertx;
  private String name;
  private boolean isDeployed = false;

  @Parameterized.Parameters
  public static Iterable<String> data() {
    return Arrays.asList("Tom", "Dick", "Harry");
  }

  public ParameterizedTest(String name) {
    this.name = name;
  }

  @Test
  public void testSomething(TestContext context) {
    System.out.println(this.name);
  }

}
/*
Tom
Dick
Harry
 */
