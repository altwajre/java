package com.company.app;

import java.util.function.Predicate;

// https://blog.idrsolutions.com/2015/02/java-8-method-references-explained-5-minutes/
// method reference - static method
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
/*
Use case: A boolean expression
public interface Predicate<T>{
  boolean test(T t);
}
 */
        // lambda
        // true
        System.out.println("#lambda: (Apple apple) -> apple.getWeight() > 150");
        Predicate<Apple> predicate = (Apple apple) -> apple.getWeight() > 150;
        System.out.println(predicate.test(new Apple(200, "green")));

        // method reference: static method
        // true
        System.out.println("#method reference static method: Apple::isHeavy");
        Predicate<Apple> mr = Apple::isHeavy;
        System.out.println(mr.test(new Apple(180, "red")));
    }
}
/*
output:
#lambda: (Apple apple) -> apple.getWeight() > 150
true
#method reference static method: Apple::isHeavy
true
 */
