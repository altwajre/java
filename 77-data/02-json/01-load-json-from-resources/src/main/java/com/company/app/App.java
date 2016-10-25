package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json//test.json");
        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);
        System.out.println(mapJson);

        Map classJson = (Map)mapJson.get("class");
        System.out.println(classJson);

        Map studentsJson = (Map)classJson.get("students");
        System.out.println(studentsJson);
    }
}
/*
input:
{
  "class":
  {
    "name": "Math",
    "students":
    {
      "123": "Tom",
      "234": "Jen"
    }
  }
}

output:
{class={name=Math, students={123=Tom, 234=Jen}}}
{name=Math, students={123=Tom, 234=Jen}}
{123=Tom, 234=Jen}
 */
