package com.company.app;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args )
    {
        TestNG testNG = new TestNG();

        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("test-all.xml");

        try {
            testNG.setXmlSuites(new Parser(inputStream).parseToList());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        testNG.run();
    }
}
