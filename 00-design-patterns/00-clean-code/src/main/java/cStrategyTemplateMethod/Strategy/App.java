package cStrategyTemplateMethod.Strategy;

/*
Definition
Allow an object to alter its behavior when its internal state changes.
The object will appear to change its class.
 */
interface Strategy{
  void algorithmInterface();
}
class ConcreteStrategyA implements Strategy{
  @Override
  public void algorithmInterface() {
    System.out.println("Called ConcreteStrategyA.algorithmInterface()");
  }
}
class ConcreteStrategyB implements Strategy{
  @Override
  public void algorithmInterface() {
    System.out.println("Called ConcreteStrategyB.algorithmInterface()");
  }
}
class ConcreteStrategyC implements Strategy{
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

public class App {
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
