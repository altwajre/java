package com.company.app;

import java.util.Arrays;

/*
 Sort String[] - use Arrays.sort()

 output:
 Dick Harry Tom
 */
public class App
{
    public static void main( String[] args )
    {
        String[] people = new String[]{"Tom", "Dick", "Harry"};
        Arrays.sort(people);
        for(String person : people){
            System.out.print(person + " ");
        }
    }
}
