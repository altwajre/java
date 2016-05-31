package com.company.app;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        TestNG testNG = new TestNG();
        List suites = Lists.newArrayList();
        suites.add("test-all.xml");  // Run all tests
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
