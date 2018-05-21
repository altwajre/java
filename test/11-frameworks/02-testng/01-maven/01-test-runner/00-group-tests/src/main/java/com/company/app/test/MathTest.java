package com.company.app.test;

import com.company.app.src.Math;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class MathTest {
    @Test (groups = {"pending"})
    public void add_test(){
        int result = Math.add(1, 2);
        Assert.assertEquals(result, 3);
    }
    @Test (groups = "smoke")
    public void subtract_test(){
        int result = Math.subtract(2, 1);
        Assert.assertEquals(result, 1);
    }
    @Test (groups = "broken")
    public void subtrac2_test(){
        int result = Math.subtract(2, 1);
        Assert.assertEquals(result, 2);
    }
}
