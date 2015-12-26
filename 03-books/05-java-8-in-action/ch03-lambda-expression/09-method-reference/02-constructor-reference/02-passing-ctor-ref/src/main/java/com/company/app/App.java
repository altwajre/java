package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

// passing a constructor reference to the map method
class Apple{
    private String name;
    private Integer weight;
    public Apple(){this("Fuji", 80);}
    public Apple(Integer weight){ this("Fuji", weight); }
    public Apple(String name, Integer weight){ this.name = name; this.weight = weight; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public Integer getWeight(){return weight;}
    public String toString(){
        return name + " " + weight;
    }
}
public class App
{
    static List<Apple> map(List<Integer> list, Function<Integer, Apple> f){
        List<Apple> result = new ArrayList<>();
        for(Integer weight : list){
            result.add(f.apply(weight));
        }
        return result;
    }
    public static void main( String[] args )
    {
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new); // passing a constructor reference to the map method
        System.out.println(apples);
    }
}
/*
output:
[Fuji 7, Fuji 3, Fuji 4, Fuji 10]
 */
