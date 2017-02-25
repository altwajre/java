package com.company.app;

import com.company.app.test.AllTests;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class App
{
    public static void main( String[] args )
    {
        JUnitCore jUnitCore = new JUnitCore();

        Result result = jUnitCore.run(AllTests.class);

        if(result.wasSuccessful()){
            System.out.println("All Tax Test cases ran successfully");
        }
        else{
            System.out.println("These Tax Test cases failed");
            result.getFailures().forEach(System.out::println);
        }
    }
}
/*

output:
These Tax Test cases failed
add_test(com.company.app.test.MyMathTest): expected:<4> but was:<3>
calc_tax_test(com.company.app.test.TaxTest): expected:<9.0> but was:<8.0>
 */
