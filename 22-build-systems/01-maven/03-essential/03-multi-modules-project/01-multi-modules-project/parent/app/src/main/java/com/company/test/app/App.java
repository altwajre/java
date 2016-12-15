package com.company.test.app;

import com.company.test.lib.Math;

public class App
{
    public static void main( String[] args )
    {
        Math math = new Math();
        System.out.printf("1 + 2 = %s\n", math.add(1, 2));
    }
}
