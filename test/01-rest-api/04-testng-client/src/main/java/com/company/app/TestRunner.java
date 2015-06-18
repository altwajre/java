package com.company.app;

import org.json.JSONObject;
import org.testng.TestNG;
import org.testng.collections.Lists;

import java.io.*;
import java.util.List;

public class TestRunner {
    public static void main( String[] args ) throws Exception {

        initialize();

        TestNG testNG = new TestNG();

        List suites = Lists.newArrayList();
        suites.add("src/main/java/testng-package.xml");  // Run all tests
        testNG.setTestSuites(suites);

        testNG.run();

        System.out.println(Global.TEST_ENV);
        System.out.println(Config.URL);
    }

    static void initialize(){
        Global.TEST_ENV = System.getenv("TEST_ENV");

        configure();
    }

    static void configure(){
        String configPath = "config/config." + Global.TEST_ENV + ".json";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(configPath);

        try{
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            JSONObject config = new JSONObject(responseStrBuilder.toString());
            System.out.println(config.get("url"));
            Config.URL = config.get("url").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
