package com.company.app;

public class App
{
    public static void main( String[] args )
    {
        Long sum = 0L; // MUCH SLOWER due to autoboxing
//        long sum = 0L;  // FAST
        for(long i = 0; i < Integer.MAX_VALUE; i++){
            sum += i;
        }
        System.out.println(sum);
    }
}
/*
output:
2305843005992468481
 */
