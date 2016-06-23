package com.company.app;

import java.util.function.Supplier;

/*
Use case: Creating objects
public interface Supplier<T>{
  T get();
}
 */
class Phone{
    private final String name;
    Phone(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}

public class App
{
    public static Phone produce(Supplier<Phone> supplier){
        return supplier.get();
    }
    public static void main( String[] args )
    {
        Phone iphone = produce(() -> new Phone("iphone"));
        System.out.println(iphone.getName());
    }
}
/*
output:
iphone
 */