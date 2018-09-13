package gof.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/*
Definition
Use sharing to support large numbers of fine-grained objects efficiently.
 */
abstract class Flyweight {
  public abstract void Operation(int extrinsicstate);
}

class ConcreteFlyweight extends Flyweight {
  @Override
  public void Operation(int extrinsicstate) {
    System.out.println("ConcreteFlyweight: " + extrinsicstate);
  }
}

class UnsharedConcreteFlyweight extends Flyweight {
  @Override
  public void Operation(int extrinsicstate) {
    System.out.println("UnsharedConcreteFlyweight: " + extrinsicstate);
  }
}

class FlyweightFactory {
  private Map flyweights = new HashMap();

  public FlyweightFactory() {
    flyweights.put("X", new ConcreteFlyweight());
    flyweights.put("Y", new ConcreteFlyweight());
    flyweights.put("Z", new ConcreteFlyweight());
  }

  public Flyweight getFlyweight(String key) {
    return (Flyweight) flyweights.get(key);
  }
}

public class Structural {
  public static void main( String[] args )
  {
    int extrinsicstate = 22;
    FlyweightFactory factory = new FlyweightFactory();
    Flyweight fx = factory.getFlyweight("X");
    fx.Operation(--extrinsicstate);

    Flyweight fy = factory.getFlyweight("Y");
    fy.Operation(--extrinsicstate);

    Flyweight fz = factory.getFlyweight("Z");
    fz.Operation(--extrinsicstate);

    UnsharedConcreteFlyweight fu = new UnsharedConcreteFlyweight();
    fu.Operation(--extrinsicstate);
  }
}
/*
ConcreteFlyweight: 21
ConcreteFlyweight: 20
ConcreteFlyweight: 19
UnsharedConcreteFlyweight: 18
 */
