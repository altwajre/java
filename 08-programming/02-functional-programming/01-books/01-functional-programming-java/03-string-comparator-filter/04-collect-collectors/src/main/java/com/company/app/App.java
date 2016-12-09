package com.company.app;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

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
        System.out.println("#mutabilityBadVersion");
        mutabilityBadVersion();
        System.out.println("#collectExplicitCall");
        collectExplicitCall();
        System.out.println("#collectorsToList");
        collectorsToList();
        System.out.println("#collectorsGroupingBy");
        collectorsGroupingBy();
        System.out.println("#collectorsGroupingByMultipleCriterias");
        collectorsGroupingByMultipleCriterias();

        System.out.println("#oldestPersonOfEachLetter");
        oldestPersonOfEachLetter();
    }

    private static void oldestPersonOfEachLetter() {
        Comparator<Person> byAge = Comparator.comparing(Person::getAge);
        Map<Character, Optional<Person>> oldestPersonOfEachLetter = people.stream()
                .collect(groupingBy(p -> p.getName().charAt(0),
                        reducing(BinaryOperator.maxBy(byAge))));
        System.out.println("Oldest person of each letter: " + oldestPersonOfEachLetter);
    }

    /*
    groupingBy takes two parameters:
    the first the age, which is the criteria to group by
    the second is a Collector, which is the result of a call to the mapping function.
     */
    private static void collectorsGroupingByMultipleCriterias() {
        Map<Integer, List<String>> nameOfPeopleByAge = people.stream()
                .collect(groupingBy(Person::getAge, mapping(Person::getName, toList())));
        System.out.println("People grouped by age: " + nameOfPeopleByAge);
    }

    private static void collectorsGroupingBy() {
        Map<Integer, List<Person>> peopleByAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("People grouped by age: " + peopleByAge);
    }

    private static void collectorsToList() {
        List<Person> olderThan20 = people.stream()
                .filter(p -> p.getAge() > 20)
                .collect(Collectors.toList());
        System.out.println("People old than 20: " + olderThan20);
    }

    private static void collectExplicitCall() {
        List<Person> olderThan20 = people.stream()
                .filter(p -> p.getAge() > 20)
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People old than 20: " + olderThan20);
    }

    /*
    Issues:
    The operation of adding an element into the target collection is low level - imperative rather than declarative.
    If we decide to make the iteration concurrent, we immediately have to deal with thread-safety concerns-the mutability
    makes it hard to parallelize.
     */
    private static void mutabilityBadVersion() {
        List<Person> olderThan20 = new ArrayList<>();
        people.stream()
                .filter(p -> p.getAge() > 20)
                // uses mutability which is BAD
                .forEach(p -> olderThan20.add(p));
        System.out.println("People older than 20: " + olderThan20);
    }
}
/*
output:
#mutabilityBadVersion
People older than 20: [Sara - 21, Jane - 21, Greg - 35]
#collectExplicitCall
People old than 20: [Sara - 21, Jane - 21, Greg - 35]
#collectorsToList
People old than 20: [Sara - 21, Jane - 21, Greg - 35]
#collectorsGroupingBy
People grouped by age: {35=[Greg - 35], 20=[John - 20], 21=[Sara - 21, Jane - 21]}
#collectorsGroupingByMultipleCriterias
People grouped by age: {35=[Greg], 20=[John], 21=[Sara, Jane]}
#oldestPersonOfEachLetter
Oldest person of each letter: {S=Optional[Sara - 21], G=Optional[Greg - 35], J=Optional[Jane - 21]}
 */
