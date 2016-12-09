package com.company.app;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/*
Use case: Select/extract from an object
public interface Function<T, R>{
  R apply(T t);
}
 */
abstract class Fruit {
    private String name;
    private Integer weight;
    public Fruit(String name, Integer weight){ this.name = name; this.weight = weight; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public Integer getWeight(){return weight;}
}
class Apple extends Fruit{
    public Apple(Integer weight){ super("Fuji", weight); }
    public Apple(String name, Integer weight){
        super(name, weight);
    }
}
class Orange extends Fruit{
    public Orange(int weight){ super("Navel", weight); }
}
public class App
{
    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }
    static Fruit giveMeFruit(String fruit, Integer weight){
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }
    public static void main( String[] args )
    {
        Fruit apple = giveMeFruit("apple", 80);
        System.out.println(apple.getName() + " " + apple.getWeight());
    }
}
/*
output:
Fuji 80
 */