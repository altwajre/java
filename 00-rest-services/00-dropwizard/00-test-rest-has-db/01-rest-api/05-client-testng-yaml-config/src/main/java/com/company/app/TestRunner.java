package com.company.app;

import com.company.app.infrastructure.Configuration;
import com.company.app.infrastructure.Global;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestRunner
{
    public static void main( String[] args ) throws IOException {
        initialize(args);
        runTest();
    }

    static void initialize(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: <file.yml>");
            return;
        }
        Yaml yaml = new Yaml();
        InputStream in = Files.newInputStream(Paths.get(args[0]));
        Configuration config = yaml.loadAs(in, Configuration.class);
        Global.Config = config;
        System.out.println(Global.Config.getUrl());
    }

    static void runTest() {
        TestNG testNG = new TestNG();
        List suites = Lists.newArrayList();
        suites.add("all-tests.xml");  // Run all tests
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
