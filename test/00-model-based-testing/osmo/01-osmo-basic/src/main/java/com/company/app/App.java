package com.company.app;

import osmo.tester.OSMOConfiguration;
import osmo.tester.OSMOTester;
import osmo.tester.generator.endcondition.Length;

public class App {
  public static void main(String[] args) {
//    generate1();
//    generate2();
    generate3();
//    generate4();
  }

  private static void generate4() {
    OSMOConfiguration config = new OSMOConfiguration();
    config.setTestEndCondition(new Length(5));
    config.setSuiteEndCondition(new Length(2));
    config.addModelObject(new HelloModel4());
    OSMOTester tester = new OSMOTester();
    tester.setConfig(config);
    tester.generate(52);
  }
/*
TEST START
HELLO
WORLD
HELLO
WORLD
WORLD
TEST END
TEST START
HELLO
WORLD
WORLD
WORLD
WORLD
TEST END
generated 2 tests.
 */

  private static void generate3() {
    OSMOConfiguration config = new OSMOConfiguration();
    config.setTestEndCondition(new Length(5));
    config.setSuiteEndCondition(new Length(2));
    config.addModelObject(new HelloModel3());
    OSMOTester tester = new OSMOTester();
    tester.setConfig(config);
    tester.generate(52);
  }
/*
TEST START
WORLD
HELLO
HELLO
WORLD
WORLD
TEST END
TEST START
HELLO
WORLD
WORLD
WORLD
WORLD
TEST END
generated 2 tests.
 */

  private static void generate2() {
    OSMOConfiguration config = new OSMOConfiguration();
    config.setTestEndCondition(new Length(5));
    config.setSuiteEndCondition(new Length(2));
    config.addModelObject(new HelloModel2());
    OSMOTester tester = new OSMOTester();
    tester.setConfig(config);
    tester.generate(52);
  }

  private static void generate1() {
    OSMOTester tester = new OSMOTester();
    tester.addModelObject(new HelloModel1());
    tester.generate(52);
  }
}
