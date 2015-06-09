package com.company.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by whan on 6/8/15.
 */
public class BeforeMethodTest {
    @BeforeMethod
    public void setUp(){
        System.out.println("\n#setUp() is invoked");
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
