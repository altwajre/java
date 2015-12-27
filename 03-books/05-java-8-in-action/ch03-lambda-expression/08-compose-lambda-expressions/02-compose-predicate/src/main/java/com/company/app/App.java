package com.company.app;

import java.applet.Applet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class Apple{
    private Integer weight = 0;
    private String color = "";
    public Apple(Integer weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public Integer getWeight(){return weight;}
    public String getColor(){return color;}
    public String toString(){return "Apple{"+"color='"+color+'\'' + ", weight="+weight+'}';}
}
public class App
{
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> results = new ArrayList<>();
        for(T t : list){
            if(predicate.test(t)){
                results.add(t);
            }
        }
        return results;
    }
    public static void main( String[] args )
    {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "red"), new Apple(120, "green"));
        Predicate<Apple> redApple = (Apple a) -> a.getColor().equals("red");

        // Predicate.negate()
        Predicate<Apple> noRedApple = redApple
                .negate(); // Produces the negation of the existing Predicate object redApple
        List<Apple> noRedApples = filter(inventory, noRedApple);
        System.out.println(noRedApples);

        // Predicate.and(Predicate<? super T> other)
        Predicate<Apple> redAndHeavyApple = redApple
                .and(a -> a.getWeight() > 150); // Chaining two predicates to produce another Predicate object
        List<Apple> redAndHeavyApples = filter(inventory, redAndHeavyApple);
        System.out.println(redAndHeavyApples);

        Predicate<Apple> redAndHeavyAppleOrGreen = redApple
                .and(a -> a.getWeight() > 150)
                .or(a -> "green".equals(a.getColor()));
        List<Apple> redAndHeavyAppleOrGreens = filter(inventory, redAndHeavyAppleOrGreen);
        System.out.println(redAndHeavyAppleOrGreens);
    }
}
/*
output:
[Apple{color='green', weight=80}, Apple{color='green', weight=120}]
[Apple{color='red', weight=155}]
[Apple{color='green', weight=80}, Apple{color='red', weight=155}, Apple{color='green', weight=120}]
 */
