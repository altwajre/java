package com.company.test;

import org.json.JSONObject;

import java.io.*;

public class App
{
    public static void main( String[] args ) throws Exception {
        StringBuilder result = new StringBuilder("");

        // Get file from resources folder
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("config/config.dev.json");

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);
        JSONObject config = new JSONObject(responseStrBuilder.toString());
        System.out.println(config.toString());
        System.out.println(config.get("test-settings"));
        System.out.println(config.getJSONObject("test-settings").get("url"));
    }
}
/*
output:
{"test-settings":{"url":"http://sitecoreadmin.int.dev-godaddy.com"}}
{"url":"http://sitecoreadmin.int.dev-godaddy.com"}
http://sitecoreadmin.int.dev-godaddy.com
 */
