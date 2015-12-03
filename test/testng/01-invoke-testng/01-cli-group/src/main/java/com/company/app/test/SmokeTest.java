package com.company.app.test;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test(groups = "smoke")
public class SmokeTest {
    public void test1(){
        Assert.assertEquals(1, 1);
    }
    public void test2(){
        Assert.assertEquals(2, 1);
    }
}
