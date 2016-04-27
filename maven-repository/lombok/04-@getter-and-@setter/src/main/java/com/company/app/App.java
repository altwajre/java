package com.company.app;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

class GetterSetterExample{
    @Getter
    @Setter
    private int age = 10;

    @Setter(AccessLevel.PROTECTED)
    private String name;

    @Override public String toString(){
        return String.format("%s (age: %d)", name, age);
    }
}
public class App {
    public static void main(String[] args){
        GetterSetterExample getterSetterExample = new GetterSetterExample();
    }
}
/*
https://projectlombok.org/features/GetterSetter.html
@Getter and/or @Setter, to let lombok generate the default getter/setter automatically.

 */
