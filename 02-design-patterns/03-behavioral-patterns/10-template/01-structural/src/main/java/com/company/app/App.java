package com.company.app;

abstract class AbstractClass{
    public abstract void primitiveOperation1();
    public abstract void primitiveOperation2();
    public void templateMethod(){
        primitiveOperation1();
        primitiveOperation2();
        System.out.println("");
    }
}
class ConcreteClassA extends AbstractClass{
    @Override
    public void primitiveOperation1() {
        System.out.println("ConcreteClassA.primitiveOperation1()");
    }
    @Override
    public void primitiveOperation2() {
        System.out.println("ConcreteClassA.primitiveOperation2()");
    }
}
class ConcreteClassB extends AbstractClass{
    @Override
    public void primitiveOperation1() {
        System.out.println("ConcreteClassB.primitiveOperation1()");
    }
    @Override
    public void primitiveOperation2() {
        System.out.println("ConcreteClassB.primitiveOperation2()");
    }
}
public class App
{
    public static void main( String[] args )
    {
        AbstractClass aA = new ConcreteClassA();
        aA.templateMethod();
        AbstractClass aB = new ConcreteClassB();
        aB.templateMethod();
    }
}
/*
Definition
Define the skeleton of an algorithm in an operation, deferring some steps to subclasses. Template Method lets subclasses
redefine certain steps of an algorithm without changing the algorithm's structure.

output:
ConcreteClassA.primitiveOperation1()
ConcreteClassA.primitiveOperation2()

ConcreteClassB.primitiveOperation1()
ConcreteClassB.primitiveOperation2()
 */
