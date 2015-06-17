package com.company.app;

import com.company.app.test.BeforeClassTest;
import org.json.JSONObject;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestRunner {
    public static void main( String[] args ) throws Exception {



        TestNG testNG = new TestNG();

        testNG.setTestClasses(new Class[]{BeforeClassTest.class});

        TestListenerAdapter listenerAdapter = new TestListenerAdapter();
        testNG.addListener(listenerAdapter);
        testNG.run();

        System.out.println("PASSED:" + listenerAdapter.getPassedTests().size());
    }

    static void setup(){

    }
}
