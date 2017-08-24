package com.company.app.test;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

class MyMath{
    public int add(int x, int y){
        return x + y;
    }
}

public class MyMathTest{
    @Test
    public void add_test(){
        MyMath myMath = new MyMath();
        int actual = myMath.add(1, 2);
        int expected = 3 + 1;
        assertEquals(expected, actual);
    }
}
