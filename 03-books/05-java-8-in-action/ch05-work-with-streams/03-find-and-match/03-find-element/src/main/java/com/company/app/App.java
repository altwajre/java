package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
The findAny method returns an arbitrary element of the current stream. It can be used in conjunction with other stream
operations.
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
        Optional<Dish> dish = Dish.menu.stream()
                .filter(Dish::isVegetarian) // d -> d.isVegetarian() - instance method of Type
                .findAny(); // returns an Optional<Dish>
        dish.ifPresent(d -> System.out.println(d.getName())); // If a value is contained it's printed; otherwise nothing happen
        System.out.println(dish);
    }
}
/*
output:
french fries
Optional[french fries]
 */
