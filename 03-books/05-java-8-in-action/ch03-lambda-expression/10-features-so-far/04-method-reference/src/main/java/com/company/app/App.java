package com.company.app;

import java.util.Arrays;
import java.util.List;

/*
Step 3: Use method references
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
    public String toString(){return "Apple{"+"color='"+color+'\'' + ", weight="+weight+'}';}
    public static int compare(Apple a1, Apple a2){
        return a1.getWeight().compareTo(a2.getWeight());
    }
}
public class App
{
    public static void main( String[] args )
    {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        inventory.sort(Apple::compare);
        System.out.println(inventory);
    }
}
/*
output:
[Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
 */
