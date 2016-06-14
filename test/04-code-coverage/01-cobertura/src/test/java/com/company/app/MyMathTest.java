package com.company.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyMathTest {
    @Test
    public void add_test() throws Exception {
        MyMath math = new MyMath();
        assertEquals(3, math.add(1,2));
    }

    @Test
    public void subtract_test() throws Exception{
//        MyMath math = new MyMath();
//        assertEquals(1, math.subtract(2,1));
    }

}