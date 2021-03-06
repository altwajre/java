package gof.structural.adapter;

/*
Definition
Convert the interface of a class into another interface clients expect. Adapter lets classes work together that
couldn't otherwise because of incompatible interfaces.
 */
class Target{
  public void Request(){
    System.out.printf("Called Target.Request()");
  }
}
class Adapter extends Target{
  private Adaptee adaptee = new Adaptee();
  @Override
  public void Request(){
    adaptee.SpecificRequest();
  }
}
class Adaptee{
  public void SpecificRequest(){
    System.out.printf("Called Adaptee.SpecificRequest()");
  }
}

public class Structural {
  public static void main( String[] args )
  {
    Target target = new Adapter();
    target.Request();
  }
}
/*
Called Adaptee.SpecificRequest()
 */
