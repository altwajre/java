package com.company.app;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        twoDimensionalArray();
        streamOneDimensionalArray();
        streamTwoDimensionalArray();
    }

    private static void twoDimensionalArray() {
        System.out.println("#twoDimensionalArray");
        Object[] row1Numbers = new Object[]{1,2,3};
        System.out.println(Arrays.asList(row1Numbers));

        Object[] row2Numbers = new Object[]{11,22,33};
        System.out.println(Arrays.asList(row2Numbers));

        Object[][] database = new Object[][]{row1Numbers, row2Numbers};
        System.out.println(Arrays.deepToString(database));
    }

    private static void streamOneDimensionalArray() {
        System.out.println("#streamOneDimensionalArray");
        List<String> names = Arrays.asList("Tom", "Dick", "Harry");
        Object[] array = names.stream()
                .map(String::length)
                .toArray(Object[]::new);
        System.out.println(Arrays.asList(array));
    }

    private static void streamTwoDimensionalArray() {
        System.out.println("#streamTwoDimensionalArray");
        List<String> names = Arrays.asList("Tom", "Dick", "Harry");
        Object[][] multi = names.stream()
                .map(String::length)
                .map(i -> new Object[]{i, i*2})
                .toArray(Object[][]::new);
        System.out.println(Arrays.deepToString(multi));
    }
}
/*
output:
#twoDimensionalArray
[1, 2, 3]
[11, 22, 33]
[[1, 2, 3], [11, 22, 33]]
#streamOneDimensionalArray
[3, 4, 5]
#streamTwoDimensionalArray
[[3, 6], [4, 8], [5, 10]]
 */