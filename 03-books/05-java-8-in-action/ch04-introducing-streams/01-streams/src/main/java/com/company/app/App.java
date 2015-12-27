package com.company.app;

import java.util.*;
import java.util.stream.Collectors;

enum Type {MEAT, FISH, OTHER}
class Dish{
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public boolean isVegetarian(){
        return vegetarian;
    }
    public int getCalories(){
        return calories;
    }
    public Type getType(){
        return type;
    }
    @Override
    public String toString(){
        return name;
    }
    public static final List<Dish> menu =
            Arrays.asList(
                    new Dish("pork", false, 800, Type.MEAT),
                    new Dish("beef", false, 700, Type.MEAT),
                    new Dish("chicken", false, 400, Type.MEAT),
                    new Dish("french fries", true, 530, Type.OTHER),
                    new Dish("rice", true, 350, Type.OTHER),
                    new Dish("season fruit", true, 120, Type.OTHER),
                    new Dish("pizza", true, 550, Type.OTHER),
                    new Dish("prawns", false, 400, Type.FISH),
                    new Dish("salmon", false, 450, Type.FISH)
            );
}
public class App
{
    public static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes){
        // filer
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d : dishes){
            if(d.getCalories() > 400){
                lowCaloricDishes.add(d);
            }
        }
        // sort
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        // map to List<String>
        for(Dish d : lowCaloricDishes){
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }
    public static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes){
        return dishes
//                .parallelStream() // Execute code in parallel
                .stream()
                .filter(d -> d.getCalories() > 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }
    public static void main( String[] args )
    {
        // Java 7
        System.out.println("Java 7");
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // Java 8
        System.out.println("Java 8");
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }
}
/*
output:
Java 7
salmon
french fries
pizza
beef
pork
---
Java 8
salmon
french fries
pizza
beef
pork
 */
