package gof.behavioral.observer.cleancode.pullmode;

import java.util.ArrayList;
import java.util.List;

/*
https://github.com/jrogalsk/patterns/tree/master/src/main/java/com/jrsoft/learning/patterns/behavioural/observer/pull_mode

Simple.
Forces observer to update it state, but does not provide any information on which data changed
Use if observed amount of data is small
 */
interface Observer {
  void update();
}

class Subject {
  private List<Observer> observers = new ArrayList<>();

  public void register(Observer o) {
    observers.add(o);
  }

  public void notifyObservers() {
    for (Observer o : observers)
      o.update();
  }

  public void remove(Observer o) {
    observers.remove(o);
  }

  public void clear() {
    observers.clear();
  }
}

public class PullMode {
  public static void main(String[] args) {
    Subject subject = new Subject();
    subject.register(() -> System.out.println("Observer_1 is notified"));
    subject.register(() -> System.out.println("Observer_2 is notified"));
    subject.notifyObservers();
  }
}
/*
Observer_1 is notified
Observer_2 is notified
 */
