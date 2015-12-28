package com.company.app;

import java.util.Comparator;

/*
Use case: Compare two objects
public interface Comparator<T>{
  int compare(T o1, T o2);
}
 */
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
        Apple redApple = new Apple(80, "red");
        Apple greenApple = new Apple(155, "green");
        Comparator<Apple> comparator = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
        System.out.println(comparator.compare(redApple, greenApple));
    }
}
/*
output:
-1
 */
