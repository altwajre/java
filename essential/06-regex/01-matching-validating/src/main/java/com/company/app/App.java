package com.company.app;

import java.util.ArrayList;
import java.util.List;


public class App
{
    public static void main( String[] args )
    {
        List<String> input = new ArrayList<>();
        input.add("123-45-6789");
        input.add("9876-5-4321");
        input.add("987-65-4321 (attack)");
        input.add("987-65-4321 ");
        input.add("192-83-7465");

        for(String ssn : input){
/*
^		match the beginning of the line
() 		group everything within the parenthesis as group 1
\d{n}	match n digits, where n is a number equal to or greater than zero
-?		optionally match a dash
$		match the end of the line
 */
            if(ssn.matches("^(\\d{3}-?\\d{2}-?\\d{4})$")){
                System.out.println("Found good SSN: " + ssn);
            }
        }
    }
}
/*
output:
Found good SSN: 123-45-6789
Found good SSN: 192-83-7465
 */