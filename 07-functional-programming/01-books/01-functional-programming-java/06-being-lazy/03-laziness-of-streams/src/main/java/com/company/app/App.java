package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class App
{
    static int length(final String name){
        System.out.println("getting length for " + name);
        return name.length();
    }
    static String toUpper(final String name){
        System.out.println("converting to uppercase: " + name);
        return name.toUpperCase();
    }
    static List<String> names = Arrays.asList("Brad", "Kate", "Kim", "Jack", "Joe",
            "Mike", "Susan", "George", "Robert", "Julia", "Parker", "Benson");
    public static void main( String[] args )
    {
        System.out.println("#methodEvaluationOrder");
        methodEvaluationOrder();

        System.out.println("#peedingLaziness");
        peedingLaziness();
    }

    /*
    from the output we can see the intermediate operations delayed their real work until last responsible moment, when
    the terminal operation was invoked. And even then, they only did the minimum work necessary to satisfy the terminal
    operation.
     */
    private static void peedingLaziness() {
        Stream<String> namesWith3Letters = names.stream()
                .filter(name -> length(name) == 3) // intermediate operation
                .map(name -> toUpper(name)); // intermediate operation
        System.out.println("Stream created, filtered, mapped...");
        System.out.println("ready to call findFirst...");
        final String firstNameWith3Letters = namesWith3Letters
                .findFirst() // terminal operation
                .get();
    }
    /*
output:
Stream created, filtered, mapped...
ready to call findFirst...
getting length for Brad
getting length for Kate
getting length for Kim
converting to uppercase: Kim

     */

    /*
    evaluation order:
    1, finds the first element that satisfies the condition length(name) == 3
    2, it passes the element to next method in the chain map() that does its part toUpper(), and passes it down the chain
    3, when the evaluation reaches the end, the terminal operation checks to see if it has received the result it's looking for
       if the terminal operation got what it needed, the computation of the chain terminates.
     */
    private static void methodEvaluationOrder() {
        final String firstNameWith3Letters = names.stream()
                .filter(name -> length(name) == 3) // intermediate operation
                .map(name -> toUpper(name)) // intermediate operation
                .findFirst() // terminal operation
                .get();
        System.out.println(firstNameWith3Letters);
    }
    /*
output:
getting length for Brad
getting length for Kate
getting length for Kim
converting to uppercase: Kim
KIM
     */
}
