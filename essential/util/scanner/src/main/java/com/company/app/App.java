package com.company.app;

import java.util.Scanner;

// https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html
// A Scanner breaks its input into tokens using a delimiter pattern, which by default matches whitespace.
public class App
{
    /*
    output:
    2 next(): a 1
    next(): a
    nextInt(): 1
    print whole string: a 1 b 2 c 3 d
     */
    public static void main( String[] args )
    {
        String input = "a\n1  b     2\n   c     3   d";
        nextTest(input);
        nextIntTest(input);
        printWholeString(input);
        nextCharAtTest(input);
        Scanner scanner = new Scanner(input);
        System.out.print(scanner.next().charAt(0) - 'a');
    }
    private static void nextCharAtTest(String input) {
        Scanner scanner = new Scanner(input);
        System.out.print(scanner.next().charAt(0) + " ");
        System.out.print(scanner.next().charAt(0) + " \n");
    }
    private static void printWholeString(String input) {
        System.out.print("print whole string: ");
        Scanner scanner = new Scanner(input);
        while(scanner.hasNext()){
            System.out.print(scanner.next() + " ");
        }
        System.out.println("");
    }
    private static void nextIntTest(String input) {
        Scanner scanner = new Scanner(input);
        System.out.println("next(): " + scanner.next());
        System.out.println("nextInt(): " + scanner.nextInt());
    }
    private static void nextTest(String input) {
        int count = 2;
        System.out.print(count + " next(): ");
        Scanner scanner = new Scanner(input);
        for(int i = 0; i < count; i++){
            System.out.print(scanner.next() + " ");
        }
        System.out.println("");
    }
}
