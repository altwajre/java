package com.company.app;

import java.util.ArrayList;

abstract class Subject{
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer){
        observers.remove(observer);
    }
    public void notifyChange(){ this.observers.forEach(o -> o.update()); }
}
class ConcreteSubject extends Subject{
    private String subjectState;
    public String getSubjectState(){
        return subjectState;
    }
    public void setSubjectState(String value){
        subjectState = value;
    }
}
abstract class Observer{
    public abstract void update();
}
class ConcreteObserver extends Observer{
    private String name;
    private String observerState;
    private ConcreteSubject subject;
    public ConcreteObserver(ConcreteSubject subject, String name){
        this.subject = subject;
        this.name = name;
    }
    @Override
    public void update() {
        observerState = subject.getSubjectState();
        System.out.printf("Observer %s's new state is %s\n", name, observerState);
    }
    public ConcreteSubject getSubject(){
        return subject;
    }
    public void setSubject(ConcreteSubject subject){
        this.subject = subject;
    }
}
public class App
{
    public static void main( String[] args )
    {
        ConcreteSubject s = new ConcreteSubject();
        s.attach(new ConcreteObserver(s, "X"));
        s.attach(new ConcreteObserver(s, "Y"));
        s.attach(new ConcreteObserver(s, "Z"));

        s.setSubjectState("ABC");
        s.notifyChange();
    }
}
/*
Definition
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are
notified and updated automatically.

output:
Observer X's new state is ABC
Observer Y's new state is ABC
Observer Z's new state is ABC
 */
