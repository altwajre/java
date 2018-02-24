package com.company.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class Apple{
    private int weight = 0;
    private String color = "";
    public Apple(int weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public int getWeight(){ return weight; }
    public void setWeight(int weight){ this.weight = weight; }
    public String getColor(){ return color; }
    public void setColor(String color){ this.color = color; }
    public String toString(){ return "Apple{" + "color=" + color +'\'' + ", weight=" + weight +'}'; }
}
public class App
{
    // 2.1.1 First attempt: filtering green apples
    public static List<Apple> filterGreenApple(List<Apple> inventory){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if("green".equals(apple.getColor())){
                result.add(apple);
            }
        }

        return result;
    }
    // 2.1.2 Second attempt: parameterizing the color
    public static List<Apple> filterApplesByColor(List<Apple> inventory, String color){
        List<Apple> result = new ArrayList<Apple>();
        for(Apple apple : inventory){
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    //<editor-fold desc="Behavior Parameterization">
    interface ApplePredicate{
        public boolean test(Apple apple);
    }
    static class AppleHeavyPredicate implements ApplePredicate{
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }
    static class AppleGreenColorPredicate implements ApplePredicate{
        public boolean test(Apple apple){
            return "green".equals(apple.getColor());
        }
    }
    static class AppleRedAndHeavyPredicate implements ApplePredicate{
        public boolean test(Apple apple) {
            return "red".equals(apple.getColor()) && apple.getWeight() > 150;
        }
    }
    // 2.2.1 Fourth attempt: filtering by abstract criteria
    public static List<Apple> filter(List<Apple> inventory, ApplePredicate predicate){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }
    /*
    2.3.4 Seventh attempt: abstracting over List type - Generic
    public interface Predicate<T>{
      boolean test(T t);
    }
     */
    public static <T> List<T> filter2(List<T> list, Predicate<T> predicate){ // type parameter T
        List<T> result = new ArrayList<>();
        for(T e : list){
            if(predicate.test(e)){
                result.add(e);
            }
        }
        return result;
    }
    //</editor-fold>

    public static void main( String[] args )
    {


        List<Apple> greenApples = filterGreenApple(inventory);
        System.out.println(greenApples); // output: [Apple{color=green', weight=80}, Apple{color=green', weight=155}]

        List<Apple> redApples = filterApplesByColor(inventory, "red");
        System.out.println(redApples); // output: [Apple{color=red', weight=120}]

        List<Apple> greenApples2 = filter(inventory, new AppleGreenColorPredicate());
        System.out.println(greenApples2); // output: [Apple{color=green', weight=80}, Apple{color=green', weight=155}]

        List<Apple> heavyApples = filter(inventory, new AppleHeavyPredicate());
        System.out.println(heavyApples); // output: [Apple{color=green', weight=155}]

        List<Apple> redHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redHeavyApples); // output: []

        // 2.3.2 Fifth attempt: using an anonymous class - lots of boilerplate code
        List<Apple> redApples2 = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals("red");
            }
        });
        System.out.println(redApples2); // output: [Apple{color=red', weight=120}]

        // 2.3.3 Sixth attempt: using a lambda expression
        List<Apple> redApples3 = filter(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApples3); // output: [Apple{color=red', weight=120}]

        // 2.3.4 Seventh attempt: abstracting over List type - Generic
        List<Apple> redApples4 = filter2(inventory, (Apple apple) -> "red".equals(apple.getColor()));
        System.out.println(redApples4); // output: [Apple{color=red', weight=120}]
        List<Integer> eventNumbers = filter2(Arrays.asList(1,2,3,4), (Integer i) -> i % 2 == 0);
        System.out.println(eventNumbers); // output: [2, 4]
    }
}
/*
output:
[Apple{color=green', weight=80}, Apple{color=green', weight=155}]
[Apple{color=red', weight=120}]
[Apple{color=green', weight=80}, Apple{color=green', weight=155}]
[Apple{color=green', weight=155}]
[]
[Apple{color=red', weight=120}]
[Apple{color=red', weight=120}]
[Apple{color=red', weight=120}]
[2, 4]
 */
