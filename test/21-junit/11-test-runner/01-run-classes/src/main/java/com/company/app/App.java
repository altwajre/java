package com.company.app;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class App
{
    public static void main( String[] args )
    {
        Result result = JUnitCore.runClasses(MyMathTest.class);

        result.getFailures().forEach(System.out::println);

        System.out.println(result.wasSuccessful());
    }
}
/*

output:
true
 */
