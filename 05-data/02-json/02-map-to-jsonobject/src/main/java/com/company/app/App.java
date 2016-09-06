package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json//test.json");
        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);
        System.out.println("# Map Json");
        System.out.println(mapJson);

        JSONObject jsonObject = new JSONObject(mapJson);
        System.out.println("# JSONObject");
        System.out.println(jsonObject.toString());
    }
}
/*
output:
# Map Json
{class={name=Math, students={123=Tom, 234=Jen}}}
# JSONObject
{"class":{"name":"Math","students":{"123":"Tom","234":"Jen"}}}
 */
