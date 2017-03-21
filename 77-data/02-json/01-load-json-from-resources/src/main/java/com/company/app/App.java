package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws IOException {
        classLoader_test();

        inputStream_test();
    }

    private static void inputStream_test() throws IOException {
        System.out.println("#inputStream_test");

        InputStream inputStream = new FileInputStream(new File("src/main/resources/json/test.json"));
        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);
        System.out.println(mapJson);

        Map classJson = (Map)mapJson.get("class");
        System.out.println(classJson);

        Map studentsJson = (Map)classJson.get("students");
        System.out.println(studentsJson);
    }
/*
output:
#inputStream_test
{class={name=Math, students={123=Tom, 234=Jen}}}
{name=Math, students={123=Tom, 234=Jen}}
{123=Tom, 234=Jen}
 */

    private static void classLoader_test() throws IOException {
        System.out.println("#classLoader_test");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json//test.json");
        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);
        System.out.println(mapJson);

        Map classJson = (Map)mapJson.get("class");
        System.out.println(classJson);

        Map studentsJson = (Map)classJson.get("students");
        System.out.println(studentsJson);
    }
/*
output:
#classLoader_test
{class={name=Math, students={123=Tom, 234=Jen}}}
{name=Math, students={123=Tom, 234=Jen}}
{123=Tom, 234=Jen}
 */
}
