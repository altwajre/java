package gof.behavioral.observer.cleancode.pushmode;

import java.util.ArrayList;
import java.util.List;

/*
https://github.com/jrogalsk/patterns/tree/master/src/main/java/com/jrsoft/learning/patterns/behavioural/observer/push_mode

More complex
Forces observer to update but provides information about data which changed
However pushed data might be inconsistent by the time update method is called
 */
interface Observer<T> {
  void update(T pushedData);
}
class Subject<T> {
  private List<Observer<T>> observers = new ArrayList<>();

  public void register(Observer<T> o) {
    observers.add(o);
  }

  public void notifyObservers(T pushedData) {
    for (Observer o : observers)
      o.update(pushedData);
  }

  public void remove(Observer<T> o) {
    observers.remove(o);
  }

  public void clear() {
    observers.clear();
  }
}
public class PushMode {
  public static void main(String[] args) {
    Subject<String> subject = new Subject<>();
    subject.register((String msg) -> System.out.println("Tom received " + msg));
    subject.register((String msg) -> System.out.println("Harry received " + msg));
    subject.notifyObservers("msft=88");
    subject.notifyObservers("yhoo=18");
  }
}
/*
Observer_1 received msft=88
Observer_2 received msft=88
Observer_1 received yhoo=18
Observer_2 received yhoo=18
 */
