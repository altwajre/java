package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Use case: A boolean expression
public interface Predicate<T>{
  boolean test(T t);
}
Streams support the limit(n) method, which returns another stream that's no longer than a given size. The requested size
is passed as argument to limit. If the stream is ordered, the first elements are returned up to a maximum of n.
 */
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
    public static void main( String[] args )
    {
        List<Dish> dishes = Dish.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishes);
    }
}
/*
output:
[pork, beef, chicken]
 */
