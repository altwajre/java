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
                {"one", "o"}, {"two", "t"}
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
        System.out.println("testMethod() - param=" + param + " s=" + s);
    }
}
/*
output:
Before SimpleTest class executed.
testMethod() - param=one s=o
Before SimpleTest class executed.
testMethod() - param=two s=t
 */
