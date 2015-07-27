package com.company.app;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class SimpleTest {
    private String param = "";
    private String s = "";
    @DataProvider
    public static Object[][] dataMethod(){
        String[][] data = new String[][]{
                new String[]{"one", "o"}, new String[]{"two", "t"}
        };
        return data;
    }
    @Factory(dataProvider = "dataMethod")
    public SimpleTest(String param, String s){
        this.param = param;
        this.s = s;
    }
    @BeforeClass
    public void beforeClass(){
        System.out.println("Before SimpleTest class executed.");
    }
    @Test
    public void testMethod(){
        System.out.println("testMethod parameter value is: " + param + " " + s);
    }
}
