package com.company.app;

import nu.xom.Builder;
import nu.xom.Document;

import java.io.InputStream;

/*
load xml from resources
 */
public class App
{
    public static void main( String[] args ) throws Exception {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("test.xml");

        Builder builder = new Builder();
        Document document = builder.build(inputStream);

        System.out.println(document.toXML());
    }
}
/*
output:

<?xml version="1.0"?>
<Person>
    <name>Tom</name>
    <age>10</age>
</Person>
 */
