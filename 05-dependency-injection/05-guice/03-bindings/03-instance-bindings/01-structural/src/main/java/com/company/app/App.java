package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.name.Names;

class MyModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Integer.class)
                .toInstance(18);
        bind(String.class)
                .toInstance("Tom");
    }
}
public class App
{
    public static void main( String[] args )
    {
        System.out.println(Guice.createInjector(new MyModule()).getInstance(Integer.class));
        System.out.println(Guice.createInjector(new MyModule()).getInstance(String.class));
    }
}
/*
https://github.com/google/guice/wiki/InstanceBindings
You can bind a type to a specific instance of that type. This is usually only useful only for objects that don't have
dependencies of their own, such as value objects.

output:
18
Tom
 */