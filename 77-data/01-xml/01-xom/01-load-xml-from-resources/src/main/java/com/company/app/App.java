package com.company.app;

import nu.xom.Builder;
import nu.xom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/*
load xml from resources
 */
public class App
{
    public static void main( String[] args ) throws Exception {
        classLoader_test();

        inputStream_test();
    }

    private static void inputStream_test() throws Exception {
        System.out.println("#inputStream_test");

        InputStream inputStream = new FileInputStream(new File("src/main/resources/xml/test.xml"));

        Builder builder = new Builder();
        Document document = builder.build(inputStream);

        System.out.println(document.toXML());
    }
/*
output:
#inputStream_test
<?xml version="1.0"?>
<Person>
    <name>Tom</name>
    <age>10</age>
</Person>
 */

    private static void classLoader_test() throws Exception {
        System.out.println("#classLoader_test");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("xml/test.xml");

        Builder builder = new Builder();
        Document document = builder.build(inputStream);

        System.out.println(document.toXML());
    }
/*
output:
#classLoader_test
<?xml version="1.0"?>
<Person>
    <name>Tom</name>
    <age>10</age>
</Person>
 */
}
