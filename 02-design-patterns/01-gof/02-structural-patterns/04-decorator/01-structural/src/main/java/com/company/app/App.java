package com.company.app;

abstract class Component{
    public abstract void Operation();
}
class ConcreteComponent extends Component{
    @Override
    public void Operation() {
        System.out.println("ConcreteComponent.Operation()");
    }
}
abstract class Decorator extends Component{
    protected Component component;
    public void setComponent(Component component){
        this.component = component;
    }
    @Override
    public void Operation(){
        if(component != null){
            component.Operation();
        }
    }
}
class ConcreteDecoratorA extends Decorator{
    @Override
    public void Operation(){
        super.Operation();
        System.out.println("ConcreteDecoratorA.Operation()");
    }
}
class ConcreteDecoratorB extends Decorator{
    @Override
    public void Operation(){
        super.Operation();
        AddBehavior();
        System.out.println("ConcreteDecoratorB.Operation()");
    }
    void AddBehavior(){
        System.out.println("ConcreteDecoratorB.AddBehavior()");
    }
}
public class App
{
    public static void main( String[] args )
    {
        ConcreteComponent c = new ConcreteComponent();
        ConcreteDecoratorA d1 = new ConcreteDecoratorA();
        ConcreteDecoratorB d2 = new ConcreteDecoratorB();

        // Linked decorators
        d1.setComponent(c);
        d2.setComponent(d1);

        d2.Operation();
    }
}
/*
Definition
Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing
for extending functionality.

output:
ConcreteComponent.Operation()
ConcreteDecoratorA.Operation()
ConcreteDecoratorB.AddBehavior()
ConcreteDecoratorB.Operation()
 */
