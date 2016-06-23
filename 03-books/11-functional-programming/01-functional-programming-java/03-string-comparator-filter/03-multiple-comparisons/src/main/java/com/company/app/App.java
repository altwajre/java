package com.company.app;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Person {
    private final String name;
    private final int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(final Person other) {
        return age - other.age;
    }

    public String toString() {
        return String.format("%s - %d", name, age);
    }
}
public class App
{
    final static List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21),
            new Person("Jane", 21), new Person("Greg", 35));
    public static void main( String[] args )
    {
        System.out.println("#oneComparison");
        oneComparison();
        System.out.println("#multipleComparisons");
        multipleComparisons();
    }

    private static void multipleComparisons() {
        final Function<Person, Integer> byAge = p -> p.getAge();
        final Function<Person, String> byName = p -> p.getName();
        // The comparing method uses the logic embedded in the provided lambda expression to create a Comparator.
        // It's a higher-order function that takes in one function (Funciton) and returns another (Comparator).
        List<Person> ascendingAgeThenName = people.stream()
                // combine two lambda expressions in the sorted() to compare both age and name properties.
                .sorted(Comparator.comparing(byAge).thenComparing(byName))
                .collect(Collectors.toList());
        System.out.println(ascendingAgeThenName);
    }

    private static void oneComparison() {
        List<Person> ascendingName = people.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
        System.out.println(ascendingName);
    }
}
/*
output:
#oneComparison
[Greg - 35, Jane - 21, John - 20, Sara - 21]
#multipleComparisons
[John - 20, Jane - 21, Sara - 21, Greg - 35]
 */
