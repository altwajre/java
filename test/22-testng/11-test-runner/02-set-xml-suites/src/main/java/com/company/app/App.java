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

//        String xml = "<suite name=\"test-all\"><test name=\"Regression\" group-by-instances=\"true\"><packages><package name=\"com.company.app.test\" /></packages></test></suite>";
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(xml.getBytes());

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
/*
output:
[TestNG] Running:
  /Users/whan/Desktop/github/java-repo/7-2/java/test/22-testng/11-test-runner/02-set-xml-suites/testng.xml


===============================================
test-all
Total tests run: 2, Failures: 1, Skips: 0
===============================================
 */
