package InterfaceSegregationPrinciple;

import java.util.ArrayList;
import java.util.List;

/*
http://www.oodesign.com/interface-segregation-principle.html

Intent:
Clients should not be forced to depend upon interfaces that they don't use.

Robot does NOT implement IFeedable because it does not eat.
 */
interface IWorkable {
  void work();
}

interface IFeedable {
  void eat();
}

class Worker implements IWorkable, IFeedable {
  @Override
  public void work() {
    System.out.println("Worker work");
  }

  @Override
  public void eat() {
    System.out.println("Worker eat");
  }
}

class Robot implements IWorkable {
  @Override
  public void work() {
    System.out.println("Robot work");
  }
}

class Manager {
  IWorkable worker;

  public void setWorker(IWorkable worker) {
    this.worker = worker;
  }

  public void manage() {
    this.worker.work();
  }
}

public class App {
  public static void main(String[] args) {
    List<IWorkable> workers = new ArrayList<>();
    workers.add(new Worker());
    workers.add(new Robot());

    Manager manager = new Manager();

    workers.forEach(worker -> {
      manager.setWorker(worker);
      manager.manage();
    });
  }
}
/*
Worker work
Robot work
 */
