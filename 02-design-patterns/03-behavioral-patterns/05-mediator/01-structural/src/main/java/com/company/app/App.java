package com.company.app;

abstract class Mediator{
    public abstract void send(String message, Colleague colleague);
}
class ConcreteMediator extends Mediator{
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;
    public void setColleague1(ConcreteColleague1 colleague){
        this.colleague1 = colleague;
    }
    public void setColleague2(ConcreteColleague2 colleague){
        this.colleague2 = colleague;
    }
    @Override
    public void send(String message, Colleague colleague) {
        if(this.colleague1 == colleague){
            this.colleague2.notify(message);
        }
        else{
            this.colleague1.notify(message);
        }
    }
}
abstract class Colleague{
    protected Mediator mediator;
    public Colleague(Mediator mediator){
        this.mediator = mediator;
    }
}
class ConcreteColleague1 extends Colleague{
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }
    public void send(String message){
        mediator.send(message, this);
    }
    public void notify(String message){
        System.out.printf("Colleague1 gets message: %s\n", message);
    }
}
class ConcreteColleague2 extends Colleague{
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }
    public void send(String message){
        mediator.send(message, this);
    }
    public void notify(String message){
        System.out.printf("Colleague2 gets message: %s\n", message);
    }
}
public class App
{
    public static void main( String[] args )
    {
        ConcreteMediator m = new ConcreteMediator();
        ConcreteColleague1 c1 = new ConcreteColleague1(m);
        ConcreteColleague2 c2 = new ConcreteColleague2(m);

        m.setColleague1(c1);
        m.setColleague2(c2);

        c1.send("How are you?");
        c2.send("Fine, thanks");
    }
}
/*
Definition
Define an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by keeping objects
from referring to each other explicitly, and it lets you vary their interaction independently.

output:
Colleague2 gets message: How are you?
Colleague1 gets message: Fine, thanks
 */
