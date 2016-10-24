package com.company.app;

import java.util.Arrays;
import java.util.stream.Stream;

// https://www.mkyong.com/java8/java-8-flatmap-example/
public class App {
    public static void main(String[] args){

        String[][] data = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}};

        //Stream<String[]>
        Stream<String[]> temp = Arrays.stream(data);

        //Stream<String>, GOOD!
        Stream<String> stream = temp.flatMap(x -> Arrays.stream(x));

        stream.forEach(System.out::println);

    }
}
/*
output:
a
b
c
d
e
f
 */
