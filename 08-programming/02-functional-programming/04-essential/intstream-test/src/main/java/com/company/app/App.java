package com.company.app;

import java.util.stream.IntStream;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("#Regular");
        IntStream.range(0, 5).forEach(i -> System.out.println(i));


        System.out.println("#Parallel");
        IntStream.range(0, 5).parallel().forEach(i -> System.out.println(i));
    }
}
/*
output:
#Regular
0
1
2
3
4
#Parallel
3
4
1
0
2
 */
