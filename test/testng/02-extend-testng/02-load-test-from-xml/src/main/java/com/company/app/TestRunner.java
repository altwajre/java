package com.company.app;

import com.company.app.test.BeforeClassTest;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestRunner {
    public static void main( String[] args )
    {
        TestNG testNG = new TestNG();

//        testNG.setTestClasses(new Class[]{BeforeClassTest.class});

        TestListenerAdapter listenerAdapter = new TestListenerAdapter();
        testNG.addListener(listenerAdapter);
        testNG.run();

        System.out.println("PASSED:" + listenerAdapter.getPassedTests().size());
    }
}
