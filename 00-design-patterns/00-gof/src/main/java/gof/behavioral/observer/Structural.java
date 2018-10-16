package gof.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/*
Definition
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are
notified and updated automatically.
 */
abstract class Subject{
  private List<Observer> observers = new ArrayList<>();
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

public class Structural {
  public static void main( String[] args )
  {
    ConcreteSubject subject = new ConcreteSubject();
    subject.attach(new ConcreteObserver(subject, "X"));
    subject.attach(new ConcreteObserver(subject, "Y"));
    subject.attach(new ConcreteObserver(subject, "Z"));

    subject.setSubjectState("ABC");
    subject.notifyChange();
  }
}
/*
Observer X's new state is ABC
Observer Y's new state is ABC
Observer Z's new state is ABC
 */
