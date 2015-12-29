package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
reduction is all you need to compute maxima and minima
 */
public class App
{
    public static void main( String[] args )
    {
        List<Integer> numbers = Arrays.asList(4,5,3,9);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        max.ifPresent(i -> System.out.println(i)); // 9

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(i -> System.out.println(i)); // 3
    }
}
/*
output:
9
3
 */