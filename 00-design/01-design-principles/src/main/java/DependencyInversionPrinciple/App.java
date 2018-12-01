package DependencyInversionPrinciple;

import java.util.ArrayList;
import java.util.List;

/*
http://www.oodesign.com/dependency-inversion-principle.html

Intent:
High-level modules should not depend on low-level modules. Both should depend on abstractions.
Abstractions should not depend on details. Details should depend on abstractions.

Manager is the high-level modules.
Worker is the low-level modules.
*/
interface IWorker {
  void work();
}

class Worker implements IWorker {
  @Override
  public void work() {
    System.out.println("Worker work");
  }
}

class SuperWorker implements IWorker {
  @Override
  public void work() {
    System.out.println("SuperWorker work");
  }
}

// Manager supports the Dependency Inversion Principle because it depends on abstraction interface.
class Manager {
  IWorker worker;

  public void setWorker(IWorker worker) {
    this.worker = worker;
  }

  public void manage() {
    worker.work();
  }
}

// BadManager violates the Dependency Inversion Principle because it depends on low-level module Worker
class BadManager {
  Worker worker;

  public void setWorker(Worker worker) {
    this.worker = worker;
  }

  public void manage() {
    worker.work();
  }
}

public class App {
  public static void main(String[] args) {
    List<IWorker> workers = new ArrayList<>();
    workers.add(new Worker());
    workers.add(new SuperWorker());

    Manager manager = new Manager();

    workers.forEach(worker -> {
      manager.setWorker(worker);
      manager.manage();
    });
  }
}
/*
Worker work
SuperWorker work
 */
