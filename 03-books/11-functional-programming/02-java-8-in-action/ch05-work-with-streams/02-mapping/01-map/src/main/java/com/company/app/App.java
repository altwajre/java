package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
A common data processing idiom is to select information from certain objects.
map() method takes a function as argument. The function is applied to each element, mapping it into a new element.

Use case: Select/extract from an object
public interface Function<T, R>{
  R apply(T t);
}
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
        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName) // method reference - instance method of type
                .collect(Collectors.toList());
        System.out.println(dishNames); // [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]

        List<Integer> dishNameLengths = Dish.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(dishNameLengths); // [4, 4, 7, 12, 4, 12, 5, 6, 6]

        List<String> words = Arrays.asList("Tom", "Dick", "Harry");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths); // [3, 4, 5]
    }
}
/*
output:
[pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
[4, 4, 7, 12, 4, 12, 5, 6, 6]
[3, 4, 5]
 */
