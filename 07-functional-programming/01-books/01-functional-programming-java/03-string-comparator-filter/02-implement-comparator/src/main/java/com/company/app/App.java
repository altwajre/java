package com.company.app;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
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

public class App {
    final static List<Person> people = Arrays.asList(new Person("John", 20), new Person("Sara", 21),
            new Person("Jane", 21), new Person("Greg", 35));
    private static void findOldest() {
        people.stream()
                .max(Person::ageDifference)
                .ifPresent(System.out::println);
    }
    private static void findYoungest() {
        people.stream()
                .min(Person::ageDifference)
                .ifPresent(System.out::println);
    }
    private static void sortByName() {
        people.stream()
                .sorted((p1, p2) -> p1.getName().compareTo(p2.getName()))
                .collect(Collectors.toList());
    }
    private static void reuseComparators() {
        Comparator<Person> compareAscending = (p1, p2) -> p1.ageDifference(p2);
        Comparator<Person> compareDescending = compareAscending.reversed();
        System.out.printf("Sorted in ascending: %s", people.stream()
                .sorted(compareAscending)
                .collect(Collectors.toList()));
        System.out.printf("Sorted in descending: %s", people.stream()
                .sorted(compareDescending)
                .collect(Collectors.toList()));
    }
    private static void lambdaExpressionDescending() {
        List<Person> descendingAge = people.stream()
                .sorted((p1, p2) -> p2.ageDifference(p1))
                .collect(Collectors.toList());
        System.out.println(descendingAge);
    }
    private static void methodReferenceAscending() {
        List<Person> ascendingAge = people.stream()
                // The compiler took two person instances, made the first the ageDifference method's target, and second the parameter
                .sorted(Person::ageDifference)
                .collect(Collectors.toList());
        System.out.println(ascendingAge);
    }
    private static void lambdaExpressionAscending() {
        List<Person> ascendingAge = people.stream()
                // sorted() compares two people's ages and return the different. return 0 if they're equal, return -1 if p1 < p2
                // return +1 if p1 > p2. the sorted method uses the comparison result to perform the ordering.
                .sorted((p1, p2) -> p1.ageDifference(p2))
                .collect(Collectors.toList());
        System.out.println(ascendingAge);
    }
    public static void main(String[] args) {
        System.out.println("#lambdaExpressionAscending");
        lambdaExpressionAscending();
        System.out.println("#methodReferenceAscending");
        methodReferenceAscending();
        System.out.println("#lambdaExpressionDescending");
        lambdaExpressionDescending();
        System.out.println("#reuseComparators");
        reuseComparators();
        System.out.println("#sortByName");
        sortByName();
        System.out.println("#findYoungest");
        findYoungest();
        System.out.println("#findOldest");
        findOldest();
    }
}
/*
output:
#lambdaExpressionAscending
[John - 20, Sara - 21, Jane - 21, Greg - 35]
#methodReferenceAscending
[John - 20, Sara - 21, Jane - 21, Greg - 35]
#lambdaExpressionDescending
[Greg - 35, Sara - 21, Jane - 21, John - 20]
#reuseComparators
Sorted in ascending: [John - 20, Sara - 21, Jane - 21, Greg - 35]Sorted in descending: [Greg - 35, Sara - 21, Jane - 21, John - 20]#sortByName
#findYoungest
John - 20
#findOldest
Greg - 35

 */
