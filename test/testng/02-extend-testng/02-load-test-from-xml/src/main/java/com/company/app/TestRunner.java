package com.company.app;

import com.company.app.test.BeforeClassTest;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.List;

public class TestRunner {
    public static void main( String[] args )
    {
        TestNG testNG = new TestNG();

        List suites = Lists.newArrayList();
        suites.add("src/main/java/testng.xml");
        testNG.setTestSuites(suites);

        TestListenerAdapter listenerAdapter = new TestListenerAdapter();
        testNG.addListener(listenerAdapter);
        testNG.run();

        System.out.println("PASSED:" + listenerAdapter.getPassedTests().size());
    }
}
