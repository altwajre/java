package gof.structural.facade;

class SubSystemOne{
  public void MethodOne(){
    System.out.println(" SubSystemOne Method");
  }
}
class SubSystemTwo{
  public void MethodTwo(){
    System.out.println(" SubSystemTwo Method");
  }
}
class SubSystemThree{
  public void MethodThree(){
    System.out.println(" SubSystemThree Method");
  }
}
class SubSystemFour{
  public void MethodFour(){
    System.out.println(" SubSystemFour Method");
  }
}
class Facde{
  private SubSystemOne one;
  private SubSystemTwo two;
  private SubSystemThree three;
  private SubSystemFour four;
  public Facde(){
    one = new SubSystemOne();
    two = new SubSystemTwo();
    three = new SubSystemThree();
    four = new SubSystemFour();
  }
  public void MethodA(){
    System.out.println("\nMethodA() ----");
    one.MethodOne();
    two.MethodTwo();
    four.MethodFour();
  }
  public void MethodB(){
    System.out.println("\nMethodB() ----");
    two.MethodTwo();
    three.MethodThree();
  }
}

public class Structural {
  public static void main( String[] args )
  {
    Facde facde = new Facde();
    facde.MethodA();
    facde.MethodB();
  }
}
/*
Definition
Provide a unified interface to a set of interfaces in a subsystem. Façade defines a higher-level interface that makes
the subsystem easier to use.

output:
MethodA() ----
 SubSystemOne Method
 SubSystemTwo Method
 SubSystemFour Method

MethodB() ----
 SubSystemTwo Method
 SubSystemThree Method
 */
