package com.company.app.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class BeforeClassTest {
    @BeforeGroups("smoke")
    public void setupGroups(){
        System.out.println("\n#BeforeClassTest.setupGroups() is invoked");
    }

    @BeforeClass
    public void setupClass(){
        System.out.println("\n#BeforeClassTest.setupClass() is invoked");
    }

    @Test(groups = "smoke")
    public void test_1(){
        System.out.println("#BeforeClassTest.test_1 - smoke");
    }

    @Test
    public void test_2(){
        System.out.println("#BeforeClassTest.test_2");
    }

    @Test(groups = "smoke")
    public void test_3(){
        System.out.println("#BeforeClassTest.test_3 - smoke");
    }
}
