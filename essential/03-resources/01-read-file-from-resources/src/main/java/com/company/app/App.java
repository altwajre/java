package com.company.app;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("xml/test.xml");
        String result = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(result);
    }
}
/*
output:

<Person>
    <name>Tom</name>
    <age>10</age>
</Person>
 */
