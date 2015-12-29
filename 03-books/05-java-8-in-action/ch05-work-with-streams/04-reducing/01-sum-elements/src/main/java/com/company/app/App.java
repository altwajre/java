package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
use the reduce() method to sum the elements of a list of numbers
 */
public class App
{
    public static void main( String[] args )
    {
        List<Integer> numbers = Arrays.asList(4,5,3,9);
        lambda(numbers);
        methodReference(numbers);
        noInitialValue(numbers);
    }

    private static void noInitialValue(List<Integer> numbers) {
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
        sum.ifPresent(i -> System.out.println(i)); // 21
    }

    private static void methodReference(List<Integer> numbers) {
        int sum = numbers.stream().reduce(0, Integer::sum); // Integer.sum(int a, int b)
        System.out.println(sum); // 21
    }

    private static void lambda(List<Integer> numbers) {
        int sum = numbers.stream().reduce(0, (a, b) -> a + b); // initial value is 0
        System.out.println(sum); // 21
    }
}
/*
output:
21
21
21
 */