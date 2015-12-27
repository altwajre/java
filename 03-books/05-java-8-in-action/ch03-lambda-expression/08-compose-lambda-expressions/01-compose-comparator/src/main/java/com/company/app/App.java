package com.company.app;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
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
    public String getColor(){return color;}
    public String toString(){return "Apple{"+"color='"+color+'\'' + ", weight="+weight+'}';}
}
public class App
{
    public static void main( String[] args )
    {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

/*
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
            (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }
 */
//        inventory.sort(Comparator.comparing((a) -> a.getWeight())); // or a more compact form
        Comparator<Apple> comparator = Comparator.comparing((Apple a) -> a.getWeight());
        inventory.sort(comparator
                .reversed()  // sorting by decreasing weight
                .thenComparing(Apple::getColor) // chaining comparators: sorting further by color when two applies have same weight
        );
        System.out.println(inventory);
    }
}
/*
output:
[Apple{color='green', weight=155}, Apple{color='red', weight=120}, Apple{color='green', weight=80}]
 */
