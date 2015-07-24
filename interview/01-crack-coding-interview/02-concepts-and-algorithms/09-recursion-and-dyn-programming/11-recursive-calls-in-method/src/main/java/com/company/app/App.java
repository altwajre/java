package com.company.app;

/*
output
first1
first2
second1
first3
second1
second2
second1
 */
public class App 
{
    public static void main( String[] args )
    {
        print(3, "first");
        System.out.println( "done" );
    }
    static void print(int n, String msg){
        if(n == 0) return;
        print(n-1, msg);
        System.out.println(msg + n);
        print(n - 1, "second");
    }
}
