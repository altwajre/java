package com.company.app;

// Abuse of ordinal to derive an associated value - DON"T DO THIS
enum Ensemble {
    SOLO, DUET, TRIO, QUARTET, QUINTET, SEXTET, SEPTET, OCTET, NONET, DECTET;
    public int numberOfMusicians() {return ordinal() + 1;}
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("SOLO="+Ensemble.SOLO.numberOfMusicians());
        System.out.println("QUARTET="+Ensemble.QUARTET.numberOfMusicians());
    }
}
/*
output:
SOLO=1
QUARTET=4
 */
