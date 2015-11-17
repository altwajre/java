package com.company.app;

class Abstraction{
    protected Implementor implementor;
    public void setImplementor(Implementor implementor){
        this.implementor = implementor;
    }
    public void operation(){
        implementor.Operation();
    }
}
abstract class Implementor{
    public abstract void Operation();
}
class RefinedAbstraction extends Abstraction{
    @Override
    public void operation(){
        implementor.Operation();
    }
}
class ConcreteImplementorA extends Implementor{
    @Override
    public void Operation() {
        System.out.println("ConcreteImplementorA operation");
    }
}
class ConcreteImplementorB extends Implementor{
    @Override
    public void Operation() {
        System.out.println("ConcreteImplementorB operation");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Abstraction ab = new RefinedAbstraction();
        ab.implementor = new ConcreteImplementorA();
        ab.operation();

        ab.implementor = new ConcreteImplementorB();
        ab.operation();
    }
}
/*
Definition
Decouple an abstraction from its implementation so that the two can vary independently.

output:
ConcreteImplementorA operation
ConcreteImplementorB operation
 */
