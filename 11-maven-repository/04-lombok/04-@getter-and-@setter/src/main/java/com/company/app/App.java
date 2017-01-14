package com.company.app;

public class App {
    public static void main(String[] args){
        GetterSetterExample getterSetterExample = new GetterSetterExample();

        getterSetterExample.setAge(28);
        System.out.println(getterSetterExample.getAge());

        getterSetterExample.setName("Tom");

        System.out.println(getterSetterExample);
    }
}
/*
https://projectlombok.org/features/GetterSetter.html
@Getter and/or @Setter, to let lombok generate the default getter/setter automatically.

 */
