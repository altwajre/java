package com.company.app;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class App
{
    final static List<String> names = Arrays.asList("Tom", "Dick", "Harry");
    static void imperative(List<String> names){
        System.out.print("  for loop: ");
        for(int i = 0; i < names.size(); i++){
            System.out.print(names.get(i) + " ");
        }

        System.out.print("\n  for iterator: ");
        for(String name : names){
            System.out.print(name + " ");
        }
    }
    static void functional(List<String> names){
        System.out.print("  anonymous class: ");
        names.forEach(new Consumer<String>() {
            public void accept(String s) {
                System.out.print(s + " ");
            }
        });
        System.out.print("\n  lambda expression: ");
        names.forEach(s -> System.out.print(s + " "));
        System.out.println("\n  method reference");
        names.forEach(System.out::println);
    }
    public static void main( String[] args )
    {
        System.out.println("#imperative");
        imperative(names);
        System.out.println("\n#functional");
        functional(names);
    }
}
/*
output:
#imperative
  for loop: Tom Dick Harry
  for iterator: Tom Dick Harry
#functional
  anonymous class: Tom Dick Harry
  lambda expression: Tom Dick Harry
  method reference
Tom
Dick
Harry
 */
