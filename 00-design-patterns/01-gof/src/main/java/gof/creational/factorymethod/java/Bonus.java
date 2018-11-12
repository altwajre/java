package gof.creational.factorymethod.java;

interface PizzaBase {
  double getCost();
  String getDescription();
}
class DefaultPizza implements PizzaBase {
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 5;
  }
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return "complementry pizza as order item is not available right now with us";
  }
}
class MexicanPizza implements PizzaBase {
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 13;
  }
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return "Mexican Pizza";
  }
}
class VegPizza implements PizzaBase {
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 10;
  }
  @Override
  public String getDescription() {
    // TODO Auto-generated method stub
    return "Veg Pizza";
  }
}
class PizzaFactory {
  static public PizzaBase getPizza(String pizzaType) {
    PizzaBase pizza = null;
    if (pizzaType.equalsIgnoreCase("veg")) {
      pizza = new VegPizza();
    } else if (pizzaType.equalsIgnoreCase("mexican")) {
      pizza = new MexicanPizza();
    } else {
      pizza = new DefaultPizza();
    }
    return pizza;
  }
}
public class Bonus {
  public static void main(String... args) {
    PizzaBase pizza = PizzaFactory.getPizza("mexican");

    System.out.println("Your final order is");
    System.out.println(pizza.getDescription());
    System.out.println("Toal cost of order is " + pizza.getCost());
  }
}
/*
Your final order is
Mexican Pizza
Toal cost of order is 13.0
 */
