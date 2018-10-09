package gof.behavioral.strategy;

/*
Definition
Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.
 */
abstract class Strategy{
  public abstract void algorithmInterface();
}
class ConcreteStrategyA extends Strategy{
  @Override
  public void algorithmInterface() {
    System.out.println("Called ConcreteStrategyA.algorithmInterface()");
  }
}
class ConcreteStrategyB extends Strategy{
  @Override
  public void algorithmInterface() {
    System.out.println("Called ConcreteStrategyB.algorithmInterface()");
  }
}
class ConcreteStrategyC extends Strategy{
  @Override
  public void algorithmInterface() {
    System.out.println("Called ConcreteStrategyC.algorithmInterface()");
  }
}
class Context{
  private Strategy strategy;
  public Context(Strategy strategy){
    this.strategy = strategy;
  }
  public void contextInterface(){
    strategy.algorithmInterface();
  }
}

public class Structural {
  public static void main( String[] args )
  {
    Context context;

    context = new Context(new ConcreteStrategyA());
    context.contextInterface();

    context = new Context(new ConcreteStrategyB());
    context.contextInterface();

    context = new Context(new ConcreteStrategyC());
    context.contextInterface();
  }
}
/*
Called ConcreteStrategyA.algorithmInterface()
Called ConcreteStrategyB.algorithmInterface()
Called ConcreteStrategyC.algorithmInterface()
 */
