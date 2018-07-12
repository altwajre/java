package com.company.app;

import org.testng.TestNG;
import org.testng.xml.Parser;

import java.io.IOException;
import java.io.InputStream;

public class TestRunner {
  public static void main(String[] args) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    String testSuiteName = "test-all.xml"; // default
    String testSuiteEnv = System.getenv("TEST_SUITE");
    if(testSuiteEnv != null) {
      testSuiteName = testSuiteEnv;
    }

    InputStream inputStream = classLoader.getResourceAsStream(testSuiteName);

    TestNG testNG = new TestNG();

    try {
      testNG.setXmlSuites(new Parser(inputStream).parseToList());
    } catch (IOException e) {
      e.printStackTrace();
    }

    testNG.run();
  }
}
