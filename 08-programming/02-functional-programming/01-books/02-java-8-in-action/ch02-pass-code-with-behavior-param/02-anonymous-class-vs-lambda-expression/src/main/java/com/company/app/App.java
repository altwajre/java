package com.company.app;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
public interface Comparator<T>{
  public int compare(T o1, T o2);
}
 */
class Apple{
    private Integer weight = 0;
    private String color = "";
    public Apple(Integer weight, String color){
        this.weight = weight;
        this.color = color;
    }
    public Integer getWeight(){ return weight; }
    public void setWeight(int weight){ this.weight = weight; }
    public String getColor(){ return color; }
    public void setColor(String color){ this.color = color; }
    public String toString(){ return "Apple{" + "color=" + color +'\'' + ", weight=" + weight +'}'; }
}
public class App
{
    public static void main( String[] args )
    {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

        // anonymous class - ascending sort
        System.out.println("#anonymous class - ascending sort");
        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
        System.out.println(inventory);

        // lambda expression - descending sort
        System.out.println("#lambda expression - descending sort");
        inventory.sort((Apple a1, Apple a2) -> a2.getWeight().compareTo(a1.getWeight()));
        System.out.println(inventory);
    }
}
/*
output:
#anonymous class - ascending sort
[Apple{color=green', weight=80}, Apple{color=red', weight=120}, Apple{color=green', weight=155}]
#lambda expression - descending sort
[Apple{color=green', weight=155}, Apple{color=red', weight=120}, Apple{color=green', weight=80}]
 */
