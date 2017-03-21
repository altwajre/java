package com.company.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws Exception {
        classLoader_test();

        inputStream_test();

    }

    private static void inputStream_test() throws IOException {
        System.out.println("#inputStream_test");

        InputStream inputStream = new FileInputStream(new File("src/main/resources/config/config.dev.json"));

        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);

        JSONObject jsonObject = new JSONObject(mapJson);
        System.out.println(jsonObject.toString());
    }
/*
output:
#inputStream_test
{"test-settings":{"url":"http://sitecoreadmin.int.dev-company.com"}}
 */

    private static void classLoader_test() throws IOException {
        System.out.println("#classLoader_test");

        // Get file from resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config/config.dev.json");

        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);

        JSONObject jsonObject = new JSONObject(mapJson);
        System.out.println(jsonObject.toString());

    }
/*
output:
#classLoader_test
{"test-settings":{"url":"http://sitecoreadmin.int.dev-company.com"}}
 */
}
