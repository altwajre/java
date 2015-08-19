package com.company.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
output:
Enter something here:
Hello
Hello

 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Enter something here: " );

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        System.out.println(input);
    }
}
