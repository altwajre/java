# Interface Segregation Principle (ISP)

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch10.xhtml

Statically typed languages like Java force programmers to create declarations that users must `import` or `include`.
It is these included declarations in source code that create the source code dependencies that force recompilation and redeployment.

Dynamically typed languages like Ruby and Python don't have declarations. They are inferred at runtime.
Thus there are no source code dependencies to force recompilation and redeployment.

## ISP and Architecture

It is harmful to depend on modules that contain more than you need.

http://www.oodesign.com/interface-segregation-principle.html

> Intent

Clients should not be forced to depend upon interfaces that they don't use.

Robot does NOT implement IFeedable because it does not eat.

```
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
```
