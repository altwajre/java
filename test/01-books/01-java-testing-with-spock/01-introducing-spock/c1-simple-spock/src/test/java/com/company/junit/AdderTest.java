package com.company.junit;

import com.company.Adder;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdderTest {
    @Test
    public void add_test(){
        int expected = 3;

        Adder adder = new Adder();
        int actual = adder.add(1, 2);

        assertEquals(expected, actual);
    }
}