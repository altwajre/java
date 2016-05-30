package com.company.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class MyMath{
    public int add(int x, int y){
        return x + y;
    }
}

public class MyMathTest{
    @Test
    public void add_test(){
        MyMath myMath = new MyMath();
        int result = myMath.add(1, 2);
        int expected = 3;
        assertEquals(expected, result);
    }
}
