package com.company.app;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;

import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws ParsingException, IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("test.xml");

        Builder builder = new Builder();
        Document document = builder.build(inputStream);

        System.out.println("# Before Modify: person.id=123");
        System.out.println(document.toXML());

        Nodes nodes = document.query("//person");

        Element target = (Element)nodes.get(0);
        Attribute id = target.getAttribute("id");
        id.setValue("888");

        System.out.println("# After Modified: person.id=888");
        System.out.println(document.toXML());
    }
}
/*
output:

# Before Modify: person.id=123
<?xml version="1.0"?>
<people>
    <person id="123">
        <name>Tom</name>
        <age>10</age>
    </person>
</people>

# After Modified: person.id=888
<?xml version="1.0"?>
<people>
    <person id="888">
        <name>Tom</name>
        <age>10</age>
    </person>
</people>
 */
