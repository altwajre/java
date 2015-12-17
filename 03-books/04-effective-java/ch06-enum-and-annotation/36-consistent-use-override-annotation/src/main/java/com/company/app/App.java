package com.company.app;

// @Override will identify the override issue for you
class Bigram{
    private final char first;
    private final char second;
    Bigram(char first, char second) {
        this.first = first;
        this.second = second;
    }
    @Override
    public boolean equals(Bigram b){
        return b.first == first && b.second == second;
    }
    public int hashCode(){
        return 31 * first + second;
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
/*
output:
Error:(10, 5) java: method does not override or implement a method from a supertype
 */
