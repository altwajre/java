package com.company.app;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/*
Use case: Creating objects
public interface Supplier<T>{
  T get();
}

Use case: Select/extract from an object
public interface Function<T, R>{
  R apply(T t);
}
 */
class Apple{
    private String name;
    private Integer weight;
    public Apple(){this("Fuji", 80);}
    public Apple(Integer weight){ this("Fuji", weight); }
    public Apple(String name, Integer weight){ this.name = name; this.weight = weight; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public Integer getWeight(){return weight;}
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println("#default ctor - ctor ref: Supplier<Apple> p1 = Apple::new;");
        Supplier<Apple> p1 = Apple::new;
        System.out.println(p1.get().getClass().getSimpleName());

        System.out.println("#default ctor - lambda: Supplier<Apple> lp1 = () -> new Apple();");
        Supplier<Apple> lp1 = () -> new Apple();
        System.out.println(lp1.get().getClass().getSimpleName());

        System.out.println("#1 arg ctor - ctor ref: Function<Integer, Apple> fruitSupplier = Apple::new;");
        Function<Integer, Apple> fruitSupplier = Apple::new;
        System.out.println(fruitSupplier.apply(80).getClass().getSimpleName());

        System.out.println("#1 arg ctor - lambda: Function<Integer, Apple> lfs = (Integer weight) -> new Apple(weight);");
        Function<Integer, Apple> lfs = (Integer weight) -> new Apple(weight);
        System.out.println(lfs.apply(80).getClass().getSimpleName());

        System.out.println("#2 args ctor - ctor ref: BiFunction<String, Integer, Apple> fruitSupplier2 = Apple::new;");
        BiFunction<String, Integer, Apple> fruitSupplier2 = Apple::new;
        System.out.println(fruitSupplier2.apply("Fuji", 80).getClass().getSimpleName());

        System.out.println("#2 args ctor - lambda: BiFunction<String, Integer, Apple> bfs2 = (String name, Integer weight) -> new Apple(name, weight);");
        BiFunction<String, Integer, Apple> bfs2 = (String name, Integer weight) -> new Apple(name, weight);
        System.out.println(bfs2.apply("Fuji", 80).getClass().getSimpleName());
    }
}
/*
output:
#default ctor - ctor ref: Supplier<Apple> p1 = Apple::new;
Apple
#default ctor - lambda: Supplier<Apple> lp1 = () -> new Apple();
Apple
#1 arg ctor - ctor ref: Function<Integer, Apple> fruitSupplier = Apple::new;
Apple
#1 arg ctor - lambda: Function<Integer, Apple> lfs = (Integer weight) -> new Apple(weight);
Apple
#2 args ctor - ctor ref: BiFunction<String, Integer, Apple> fruitSupplier2 = Apple::new;
Apple
#2 args ctor - lambda: BiFunction<String, Integer, Apple> bfs2 = (String name, Integer weight) -> new Apple(name, weight);
Apple
 */
