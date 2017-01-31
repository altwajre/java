package com.company.app;

public class App
{
    // Simple use of varargs
    static int sum(int... args){
        int sum = 0;
        for(int arg : args){
            sum += arg;
        }
        return sum;
    }

    // The right way to use varargs to pass one or more arguments
    static int min(int firstArg, int... remainingArgs){
        int min = firstArg;
        for(int arg : remainingArgs){
            if(arg < min){
                min = arg;
            }
        }
        return min;
    }

    public static void main( String[] args )
    {
        System.out.println(sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(min(1));
    }
}
/*
output:
55
1
 */
