package com.company.app;

import org.junit.Assert;
import org.junit.Test;

public class MyMathTest {
    @Test
    public void add() {
        int result = new MyMath().add(2, 3);
        Assert.assertEquals(4, result);
    }

}