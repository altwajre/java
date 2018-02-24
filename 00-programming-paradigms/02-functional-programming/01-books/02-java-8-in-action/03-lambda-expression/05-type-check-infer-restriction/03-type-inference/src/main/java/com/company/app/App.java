package com.company.app;

import java.util.Arrays;
import java.util.List;

// Java compiler infers the types of the parameters of a lambda
class Apple{
    private Integer weight = 0;
    private String color = "";
    public Apple(Integer weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public Integer getWeight(){return weight;}
    public String toString(){return "Apple{"+"color='"+color+'\'' + ", weight="+weight+'}';}
}
public class App
{
    public static void main( String[] args )
    {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
//        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo((a2.getWeight()))); // Without type inference
        inventory.sort((a1, a2) -> a1.getWeight().compareTo((a2.getWeight()))); // With type inference
        System.out.println(inventory);
    }
}
/*
output:
[Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
 */
