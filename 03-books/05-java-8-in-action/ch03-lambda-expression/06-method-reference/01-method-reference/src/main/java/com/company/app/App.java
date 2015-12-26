package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

// https://blog.idrsolutions.com/2015/02/java-8-method-references-explained-5-minutes/
class Apple{
    private Integer weight = 0;
    private String color = "";
    public Apple(Integer weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public Integer getWeight(){return weight;}
    public void setWeight(Integer weight){this.weight = weight;}
    public String getColor(){return color;}
    public void setColor(String color){this.color = color;}
    public String toString(){return "Apple{"+"color='"+color+'\'' + ", weight="+weight+'}';}
    static boolean isHeavy(Apple apple){ return apple.getWeight() > 150; }
}
public class App
{
    public static void main( String[] args )
    {
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(new Apple(80,"green"), new Apple(155,"green"), new Apple(120,"red")));

/*
Use case: A boolean expression
public interface Predicate<T>{
  boolean test(T t);
}
 */
        System.out.println("#lambda: (Apple apple) -> apple.getWeight() > 150");
        Predicate<Apple> predicate = (Apple apple) -> apple.getWeight() > 150;
        System.out.println(predicate.test(new Apple(200, "green")));

        System.out.println("#method reference static method: Apple::isHeavy");
        Predicate<Apple> mr = Apple::isHeavy;
        System.out.println(mr.test(new Apple(180, "red")));

        // lambda
        // [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);

        inventory.set(1, new Apple(10, "red"));
//        System.out.println(inventory);

        // method reference
        // [Apple{color='red', weight=10}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);

        // [a, A, b, B]
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);

        // [a, A, b, B]
        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(String::compareToIgnoreCase);
        System.out.println(str2);
    }
}
/*
output:
#lambda: (Apple apple) -> apple.getWeight() > 150
true
#method reference static method: Apple::isHeavy
true
[Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
[Apple{color='red', weight=10}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
[a, A, b, B]
[a, A, b, B]
 */
