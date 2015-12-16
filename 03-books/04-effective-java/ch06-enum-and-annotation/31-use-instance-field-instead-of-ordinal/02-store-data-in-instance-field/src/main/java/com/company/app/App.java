package com.company.app;

// Enum with integer data stored in an instance field
enum Ensemble{
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5), SEXTET(6), SEPTET(7), OCTET(
            8), DOUBLE_QUARTET(8), NONET(9), DECTET(10), TRIPLE_QUARTET(12);
    private final int numberOfMusicians;
    Ensemble(int size) {
        this.numberOfMusicians = size;
    }
    public int getNumberOfMusicians(){
        return numberOfMusicians;
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("SOLO size="+Ensemble.SOLO.getNumberOfMusicians());
        System.out.println("NONET size="+Ensemble.NONET.getNumberOfMusicians());
    }
}
/*
output:
SOLO size=1
NONET size=9
 */