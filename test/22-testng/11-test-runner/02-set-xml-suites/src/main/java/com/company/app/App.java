package com.company.app;

import org.testng.TestNG;
import org.testng.xml.Parser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        TestNG testNG = new TestNG();
        String xml = "<suite name=\"test-all\"><test name=\"Regression\" group-by-instances=\"true\"><packages><package name=\"com.company.app.test\" /></packages></test></suite>";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());

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
