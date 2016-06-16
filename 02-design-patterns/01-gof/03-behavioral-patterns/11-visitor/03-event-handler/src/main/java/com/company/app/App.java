package com.company.app;
abstract class Visitor{  // abstract Visitor
    public abstract void visit(Event1 event1);
    public abstract void visit(Event2 event2);
}
class Visitor1 extends Visitor{
    public void visit(Event1 event1){ doVisit(event1); }
    public void visit(Event2 event2){ doVisit(event2); }
    private void doVisit(IEvent event){
        System.out.println(this.getClass().getSimpleName() + ", " + event.getClass().getSimpleName());
    }
}
class Visitor2 extends Visitor{
    public void visit(Event1 event1){ doVisit(event1); }
    public void visit(Event2 event2){ doVisit(event2); }
    private void doVisit(IEvent event){
        System.out.println(this.getClass().getSimpleName() + ", " + event.getClass().getSimpleName());
    }
}
interface IEvent { void accept(Visitor visitor); }
class Event1 implements IEvent {
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}
class Event2 implements IEvent {
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
}
class DefaultClusterEventHandler{
    private Visitor visitor;
    public DefaultClusterEventHandler(Visitor visitor){ this.visitor = visitor; }
    public void onEvent(IEvent event){ event.accept(visitor); }
}
public class App
{
    public static void main( String[] args )
    {
        DefaultClusterEventHandler eventHandler1 = new DefaultClusterEventHandler(new Visitor1());
        eventHandler1.onEvent(new Event1());
        eventHandler1.onEvent(new Event2());
        DefaultClusterEventHandler eventHandler2 = new DefaultClusterEventHandler(new Visitor2());
        eventHandler2.onEvent(new Event1());
        eventHandler2.onEvent(new Event2());
    }
}
/*
output:
Visitor1, Event1
Visitor1, Event2
Visitor2, Event1
Visitor2, Event2
 */
