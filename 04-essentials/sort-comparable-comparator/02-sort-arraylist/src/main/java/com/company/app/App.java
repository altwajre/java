package com.company.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
 Sort List<String> - use Collections.sort()

 output:
 Dick Harry Tom
 */
public class App 
{
    public static void main( String[] args )
    {
        List<String> people = Arrays.asList(new String[]{"Tom", "Dick", "Harry"});
        Collections.sort(people);
        for(String person : people){
            System.out.print(person + " ");
        }
    }
}
