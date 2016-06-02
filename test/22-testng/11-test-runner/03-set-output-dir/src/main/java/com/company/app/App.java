package com.company.app;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();

        testNG.setOutputDirectory("test-output/0.9"); // SET OUTPUT

        List<String> suites = new ArrayList<>();
        suites.add("test-all.xml");
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
