package com.company;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalcTest {

    @Test
    public void add_test(){
        Calc calc = new Calc();
        int result = calc.add(1, 2);
        Assert.assertEquals(3, result);
    }

    @Test
    public void subtract_test(){
        Calc calc = new Calc();
        int result = calc.subtract(4, 1);
        Assert.assertEquals(2, result);
    }

}