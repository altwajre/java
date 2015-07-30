package com.company.app;

public class App
{
    static int fibonacciRecusion(int number){
        if(number == 1 || number == 2){
            return 1;
        }
        return fibonacciRecusion(number - 1) + fibonacciRecusion(number - 2);
    }

    static void fibonacciLoop(int numberOfTerms){
        int first = 0;
        int second = 1;
        int next;
        System.out.print("fibonacci: " + first + ", " + second);
        int thirdIndex = 2;
        for(int i = thirdIndex; i <= numberOfTerms; i++){
            next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
    }
    public static void main( String[] args )
    {
        testFibonacciRecusion();

        testFibonacciLoop();
    }

    static int number = 8;
    /*
      output:
      fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
      index:     0  1  2  3  4  5  6  7   8   9
     */
    private static void testFibonacciLoop() {
        System.out.println("#For loop " + number +  ": ");
        fibonacciLoop(number);
    }

    /*
                                                       (9)r34
                                                (8)r21  +  (7)r13
                                         (7)r13  +  (6)r8
                                  (6)r8  +  (5)r5
                            (5)r5  +  (4)r3
                     (4)r3  +  (3)r2
               (3)r2  +   (2)r1
         (2)r1  +  (1)r1
      output:
      34
      because
      fibonacci: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
      index:     0  1  2  3  4  5  6  7   8   9
     */
    private static void testFibonacciRecusion() {
        System.out.println("#Recusion " + number + ": ");
        System.out.println(fibonacciRecusion(number));
    }
}
