package com.company.app;

import lombok.NonNull;

class Person{
    final private String name;
    Person(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
class Something{
    public Something(String msg){
    }
}
class NonNullExample extends Something{
    private String name;
    public NonNullExample(@NonNull Person person){
        super("Hello");
        this.name = person.getName();
    }
}
public class App {
    public static void main( String... args ) {
        new NonNullExample(null);
    }
}
/*
https://projectlombok.org/features/NonNull.html

output:
Exception in thread "main" java.lang.NullPointerException
	at com.company.app.NonNullExample.<init>(App.java:22)
	at com.company.app.App.main(App.java:27)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:497)
	at com.intellij.rt.execution.application.AppMain.main(AppMain.java:144)
 */