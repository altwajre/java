package com.company.app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathTest {
    @Test
    public void add_test(){
        Math math = new Math();
        assertEquals(3, math.add(1,2));
    }
}
