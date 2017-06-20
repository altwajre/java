package com.company.app;

import org.junit.Assert;
import org.junit.Test;

public class MyMathTest {
    @Test
    public void add() {
        int expected = 4;
        int actual = new MyMath().add(2, 3);
        Assert.assertEquals(expected, actual);
    }

}