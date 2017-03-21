package com.company.app;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws IOException {
        classLoader_test();

        fileInputStream_test();
    }

    private static void fileInputStream_test() throws IOException {
        System.out.println("#fileInputStream_test");
        InputStream inputStream = new FileInputStream(new File("src/main/resources/xml/test.xml"));
        String result = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(result);
    }
/*
output:
#fileInputStream_test
<Person>
    <name>Tom</name>
    <age>10</age>
</Person>
 */

    private static void classLoader_test() throws IOException {
        System.out.println("#classLoader_test");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("xml/test.xml");
        String result = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(result);
    }
/*
output:
#classLoader_test
<Person>
    <name>Tom</name>
    <age>10</age>
</Person>
 */
}
