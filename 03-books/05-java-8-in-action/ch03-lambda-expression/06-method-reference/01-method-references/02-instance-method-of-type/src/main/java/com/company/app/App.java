package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

// method reference - instance method of Type;
class Apple{
    private Integer weight = 0;
    private String color = "";
    public Apple(){
        this(0, "unknown");
    }
    public Apple(Integer weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public Integer getWeight(){return weight;}
    public void setWeight(Integer weight){this.weight = weight;}
    public String getColor(){return color;}
    public void setColor(String color){this.color = color;}
    public String toString(){return "Apple{"+"color='"+color+'\'' + ", weight="+weight+'}';}
    public boolean isHeavy(Apple apple){ return apple.getWeight() > 150; }
    public boolean isTrue(){return false;}
}
public class App
{
    public static void main( String[] args )
    {
/*
Use case: A boolean expression
public interface Predicate<T>{
  boolean test(T t);
}
 */
        // lambda
        // true
        System.out.println("#lambda: (Apple apple) -> apple.isTrue() > 150");
        Predicate<Apple> predicate = (Apple apple) -> apple.isTrue();
        boolean result = predicate.test(new Apple());
        System.out.println(result);

        // method reference: instance method of Type
        // true
        System.out.println("#method reference - instance method of type: Apple::isTrue");
        Predicate<Apple> mr = Apple::isTrue;
        System.out.println(mr.test(new Apple()));

        List<Apple> inventory = Arrays.asList(new Apple(80,"green"), new Apple(155,"green"), new Apple(120,"red"));
/*
Use case: Compare two objects
public interface Comparator<T>{
  int compare(T o1, T o2);
}
 */
        // lambda
        // [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        System.out.println(inventory);

        // reset
        inventory.set(1, new Apple(10, "red"));
//        System.out.println(inventory);

        // method reference: instance method of Type
        // [Apple{color='red', weight=10}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        inventory.sort(comparing(Apple::getWeight));
        System.out.println(inventory);

        // lambda
        // [a, A, b, B]
        List<String> str = Arrays.asList("a","b","A","B");
        str.sort((s1,s2) -> s1.compareToIgnoreCase(s2));
        System.out.println(str);

        // method reference: instance method of Type
        // [a, A, b, B]
        List<String> str2 = Arrays.asList("a","b","A","B");
        str2.sort(String::compareToIgnoreCase);
        System.out.println(str2);
    }
}
/*
output:
#lambda: (Apple apple) -> apple.isTrue() > 150
false
#method reference - instance method of type: Apple::isTrue
false
[Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
[Apple{color='red', weight=10}, Apple{color='green', weight=80}, Apple{color='green', weight=155}]
[a, A, b, B]
[a, A, b, B]
 */
