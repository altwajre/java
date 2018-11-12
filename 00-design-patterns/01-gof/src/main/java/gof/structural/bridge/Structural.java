package gof.structural.bridge;

/*
Definition
Decouple an abstraction from its implementation so that the two can vary independently.
 */
class Abstraction{
  protected Implementor implementor;
  public void setImplementor(Implementor implementor){
    this.implementor = implementor;
  }
  public void operation(){
    implementor.Operation();
  }
}
abstract class Implementor{
  public abstract void Operation();
}
class RefinedAbstraction extends Abstraction{
  @Override
  public void operation(){
    implementor.Operation();
  }
}
class ConcreteImplementorA extends Implementor{
  @Override
  public void Operation() {
    System.out.println("ConcreteImplementorA operation");
  }
}
class ConcreteImplementorB extends Implementor{
  @Override
  public void Operation() {
    System.out.println("ConcreteImplementorB operation");
  }
}

public class Structural {
  public static void main( String[] args )
  {
    Abstraction ab = new RefinedAbstraction();
    ab.setImplementor(new ConcreteImplementorA());
    ab.operation();

    ab.implementor = new ConcreteImplementorB();
    ab.operation();
  }
}
/*
ConcreteImplementorA operation
ConcreteImplementorB operation
 */
