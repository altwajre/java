package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/*
Use case: Consuming from an object
public interface Consumer<T>{
  void accept(T t);
}
 */
public class App
{
    public static <T> void forEach(List<T> list, Consumer<T> consumer){
        for(T t : list){
            consumer.accept(t);
        }
    }
    public static void main( String[] args )
    {
        forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.print(i + " "));

        System.out.println("\n#stream forEach()");
        Arrays.asList(1,2,3,4,5).stream()
                .forEach(System.out::print);
    }
}
/*
output:
1 2 3 4 5
#stream forEach()
12345
 */
