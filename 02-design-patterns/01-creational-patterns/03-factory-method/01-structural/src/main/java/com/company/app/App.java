package com.company.app;

abstract class Product{}
class ConcreteProductA extends Product{}
class ConcreteProductB extends Product{}
abstract class Creator{
    public abstract Product factoryMethod();
}
class ConcreteCreatorA extends Creator{
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}
class ConcreteCreatorB extends Creator{
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}
public class App
{
    public static void main( String[] args )
    {
        Creator[] creators = new Creator[2];
        creators[0] = new ConcreteCreatorA();
        creators[1] = new ConcreteCreatorB();
        for(Creator creator : creators){
            Product product = creator.factoryMethod();
            System.out.printf("Create %s\n", product.getClass().getSimpleName());
        }
    }
}
/*
Definition
Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets
a class defer instantiation to subclasses.

output:
Create ConcreteProductA
Create ConcreteProductB
 */
