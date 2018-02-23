# Dependency Inversion Principle

http://www.oodesign.com/dependency-inversion-principle.html

- youtube
https://www.youtube.com/watch?v=ngdyie64xwo

> Intent

- High-level modules should not depend on low-level modules. Both should depend on abstractions.
- Abstractions should not depend on details. Details should depend on abstractions.


```
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
    this.worker.work();
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
```
