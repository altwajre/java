# Dependency Inversion Principle

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch11.xhtml

DIP tells us that the most flexible systems are those in which source code dependencies refer only to abstractions, not to concretions.

## Stable Abstractions

- Don't refer to volatile concrete classes
Refer to abstract interfaces instead

- Don't derive from volatile concrete classes
In statically typed languages, inheritance is the strongest, and most rigid, of all the source code relationships; consequently, it should be used with great care.

- Don't override concrete functions
When you override the concrete functions, you do not eliminate those dependencies.
To manage those dependencies, you should make the function abstract and create multiple implementations.

## Factories

Factories acts dependency injection to create an object.

To comply with rules, the creation of volatile concrete object requires special handling.

## References

- youtube
https://www.youtube.com/watch?v=v-GiuMmsXj4

- Code example
/design-patterns/creational-patterns/abstract-factory-structural

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
