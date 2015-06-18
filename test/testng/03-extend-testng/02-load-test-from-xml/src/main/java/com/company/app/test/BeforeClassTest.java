package com.company.app.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BeforeClassTest {
    @BeforeClass
    public void oneTimeSetup(){
        System.out.println("\n#oneTimeSetup() is invoked");
    }

    @Test
    public void test_1(){
        System.out.println("#test_1");
    }

    @Test
    public void test_2(){
        System.out.println("#test_2");
    }
}
