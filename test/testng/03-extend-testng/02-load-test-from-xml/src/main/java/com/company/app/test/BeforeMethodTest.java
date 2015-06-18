package com.company.app.test;

import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BeforeMethodTest {
    @BeforeGroups("smoke")
    public void setupGroups(){
        System.out.println("\n#BeforeMethodTest.setupGroups() is invoked");
    }

    @BeforeMethod
    public void setUpMethod(){
        System.out.println("\n#BeforeMethodTest.setUpMethod() is invoked");
    }

    @Test
    public void test_1(){
        System.out.println("#BeforeMethodTest.test_1");
    }

    @Test(groups = "smoke")
    public void test_2(){
        System.out.println("#BeforeMethodTest.test_2 - smoke");
    }

    @Test(groups = "smoke")
    public void test_3(){
        System.out.println("#BeforeMethodTest.test_3 - smoke");
    }
}
