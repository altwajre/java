package gof.creational.abstractfactory;

/*
Definition
Provide an interface for creating families of related or dependent objects without specifying their concrete classes.
 */
abstract class AbstractProductA{}

abstract class AbstractProductB{
  public abstract void Interact(AbstractProductA a);
}

class ProductA1 extends AbstractProductA{}

class ProductB1 extends AbstractProductB{
  @Override
  public void Interact(AbstractProductA a) {
    System.out.println(this.getClass().getSimpleName() + " interacts with " + a.getClass().getSimpleName());
  }
}

class ProductA2 extends AbstractProductA{}

class ProductB2 extends AbstractProductB{
  @Override
  public void Interact(AbstractProductA a) {
    System.out.println(this.getClass().getSimpleName() + " interacts with " + a.getClass().getSimpleName());
  }
}

// factory acts as dependency injection that controls how the products should be built
abstract class AbstractFactory{
  public abstract AbstractProductA CreateProductA();
  public abstract AbstractProductB CreateProductB();
}

class ConcreteFactory1 extends AbstractFactory{
  @Override
  public AbstractProductA CreateProductA() {
    return new ProductA1();
  }
  @Override
  public AbstractProductB CreateProductB() {
    return new ProductB1();
  }
}

class ConcreteFactory2 extends AbstractFactory{
  @Override
  public AbstractProductA CreateProductA() {
    return new ProductA2();
  }
  @Override
  public AbstractProductB CreateProductB() {
    return new ProductB2();
  }
}

class Client{
  private AbstractProductA abstractProductA;
  private AbstractProductB abstractProductB;
  public Client(AbstractFactory factory){
    this.abstractProductA = factory.CreateProductA();
    this.abstractProductB = factory.CreateProductB();
  }
  public void Run(){
    abstractProductB.Interact(abstractProductA);
  }
}

// Abstract Factory
public class Structural {
  public static void main( String[] args )
  {
    AbstractFactory factory1 = new ConcreteFactory1();
    Client client1 = new Client(factory1);
    client1.Run();

    AbstractFactory factory2 = new ConcreteFactory2();
    Client client2 = new Client(factory2);
    client2.Run();
  }

}
/*
ProductB1 interacts with ProductA1
ProductB2 interacts with ProductA2
 */
