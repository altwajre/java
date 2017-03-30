package com.company.app;

import java.util.Scanner;

/*
output:
Enter something here:
hello
hello

 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Enter something here: ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        System.out.println(input);
    }
}
