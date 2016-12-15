package com.company.app;

import java.util.Random;

/*
http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
 */
public class App
{
    static void testRandom(){
        Random random = new Random();
        for(int i = 0; i < 10; i++){
            // nextInt is normally exclusive of the top value. 0-7
            int upperBound = 8;  // upper bound (exclusive)
            System.out.print(random.nextInt(upperBound)+" ");
        }
        // one of outputs: 0 7 3 6 5 6 0 6 2 4
        System.out.println("");
    }
    static int randInt(int min, int max){
        Random random = new Random();
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomeNum = random.nextInt((max - min) + 1) + min;
        return randomeNum;
    }
    public static void main( String[] args )
    {
        testRandom();
        for(int i = 0; i < 10; i++) {
            System.out.print(randInt(1, 8)+" ");
        }
        // one of outputs 1 8 6 4 1 6 8 2 6 7
        System.out.println("");
    }
}
