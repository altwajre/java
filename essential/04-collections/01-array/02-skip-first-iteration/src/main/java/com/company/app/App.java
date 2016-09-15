package com.company.app;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        Integer[] integers = {1, 2, 3};
        System.out.println(Arrays.toString(integers));
        List<Integer> list = Arrays.asList(integers);
        list.stream().skip(1).forEach(i -> System.out.println(i));
    }
}
/*
output:
[1, 2, 3]
2
3
 */
