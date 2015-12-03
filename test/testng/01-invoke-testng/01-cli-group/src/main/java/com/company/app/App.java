package com.company.app;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class App
{
    public static void main( String[] args ) {
        TestNG testNG = new TestNG();
        List suites = Lists.newArrayList();
//        suites.add("test-all.xml");  // Run all tests
        // output: Total tests run: 7, Failures: 3, Skips: 0

//        suites.add("test-class.xml");  // Run tests in a class
        // output: Total tests run: 2, Failures: 1, Skips: 0

        suites.add("test-group.xml");  // Run include tests, not run exclude tests
        // output: Total tests run: 3, Failures: 1, Skips: 0

        testNG.setTestSuites(suites);
        testNG.run();
    }
}
