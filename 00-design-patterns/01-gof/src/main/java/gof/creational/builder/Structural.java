package gof.creational.builder;

import java.util.ArrayList;
import java.util.List;

/*
Definition
Separate the construction of a complex object from its representation so that the same construction process can create
different representations.
 */
abstract class Builder{
  public abstract void addPartA();
  public abstract void addPartB();
  public abstract Product build();
}
class ConcreteBuilder1 extends Builder{
  private Product product = new Product();
  @Override
  public void addPartA() {
    product.Add("PartA");
  }
  @Override
  public void addPartB() {
    product.Add("PartB");
  }
  @Override
  public Product build() {
    return product;
  }
}
class ConcreteBuilder2 extends Builder{
  private Product product = new Product();
  @Override
  public void addPartA() {
    product.Add("PartX");
  }
  @Override
  public void addPartB() {
    product.Add("PartY");
  }
  @Override
  public Product build() {
    return product;
  }
}
class Product{
  private List<String> parts = new ArrayList<String>();
  public void Add(String part){
    parts.add(part);
  }
  public void Show(){
    System.out.println("\nProduct Parts ------");
    for(String part : parts){
      System.out.println(part);
    }
  }
}
class Director{
  public void Construct(Builder builder){
    builder.addPartA();
    builder.addPartB();
  }
}
// Builder

public class Structural {
  public static void main( String[] args )
  {
    Director director = new Director();
    Builder b1 = new ConcreteBuilder1();
    Builder b2 = new ConcreteBuilder2();
    director.Construct(b1);
    Product p1 = b1.build();
    p1.Show();

    director.Construct(b2);
    Product p2 = b2.build();
    p2.Show();
  }
}
/*
Product Parts ------
PartA
PartB

Product Parts ------
PartX
PartY
 */
