package com.company.app;

import java.util.ArrayList;

/*
The Visitor pattern in which an object traverses an object structure and performs the same operation on each node in
this structure. Different visitor objects define different operations.

 */
abstract class Visitor{
    public abstract void visitConcreteElementA(ConcreteElementA concreteElementA);
    public abstract void visitConcreteElementB(ConcreteElementB concreteElementB);
}
class ConcreteVisitor1 extends Visitor{
    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.printf("%s visited by %s\n", concreteElementA.getClass().getSimpleName(), this.getClass().getSimpleName());
    }
    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.printf("%s visited by %s\n", concreteElementB.getClass().getSimpleName(), this.getClass().getSimpleName());
    }
}
class ConcreteVisitor2 extends Visitor{
    @Override
    public void visitConcreteElementA(ConcreteElementA concreteElementA) {
        System.out.printf("%s visited by %s\n", concreteElementA.getClass().getSimpleName(), this.getClass().getSimpleName());
    }
    @Override
    public void visitConcreteElementB(ConcreteElementB concreteElementB) {
        System.out.printf("%s visited by %s\n", concreteElementB.getClass().getSimpleName(), this.getClass().getSimpleName());
    }
}
abstract class Element{
    public abstract void accept(Visitor visitor);
}
class ConcreteElementA extends Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }
    public void OperationA(){ }
}
class ConcreteElementB extends Element{
    @Override
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }
    public void OperationB(){ }
}
class ObjectStructure{
    private ArrayList<Element> elements = new ArrayList<Element>();
    public void attach(Element element){
        elements.add(element);
    }
    public void detach(Element element){
        elements.remove(element);
    }
    public void accept(Visitor visitor){
        for(Element element : elements){
            element.accept(visitor);
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        // Setup structure
        ObjectStructure o = new ObjectStructure();
        o.attach(new ConcreteElementA());
        o.attach(new ConcreteElementB());

        // Create visitor objects
        ConcreteVisitor1 v1 = new ConcreteVisitor1();
        ConcreteVisitor2 v2 = new ConcreteVisitor2();

        // Structure accepting visitors
        o.accept(v1);
        o.accept(v2);
    }
}
/*
Definition
Represent an operation to be performed on the elements of an object structure. Visitor lets you define a new operation
without changing the classes of the elements on which it operates.

output:
ConcreteElementA visited by ConcreteVisitor1
ConcreteElementB visited by ConcreteVisitor1
ConcreteElementA visited by ConcreteVisitor2
ConcreteElementB visited by ConcreteVisitor2
 */
