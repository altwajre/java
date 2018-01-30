package com.company.app;

import java.util.Arrays;
import java.util.List;

/*
Checking to see if a predicate matches all elements
 */
enum Type {MEAT, FISH, OTHER}
class Dish{
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
    @Override
    public String toString(){
        return name;
    }
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
    public int getCalories(){
        return calories;
    }
    public Type getType(){
        return type;
    }
    public boolean isVegetarian(){
        return vegetarian;
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("#allMatch");
        boolean isHealthy = Dish.menu.stream()
                .allMatch(d -> d.getCalories() < 1000);
        System.out.println(isHealthy);

        System.out.println("#noneMatch");
        boolean isHealthy2 = Dish.menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy2);
    }
}
/*
output:
#allMatch
true
#noneMatch
true
 */
