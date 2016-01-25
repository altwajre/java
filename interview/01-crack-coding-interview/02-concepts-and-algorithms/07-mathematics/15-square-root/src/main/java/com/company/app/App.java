package com.company.app;

public class App
{
    static int sqrt(int n){
        int start = 0;
        int end = n;
        while(start < end){
            int mid = (start + end) / 2;
            int multiply = mid * mid;
            if(multiply == n) return mid;
            else if (multiply > n) end = mid;
            else start = mid;
        }
        return -1; // not found
    }
    public static void main( String[] args )
    {
        System.out.println(sqrt(25));  // 5
    }
}
/*
output:
5
 */