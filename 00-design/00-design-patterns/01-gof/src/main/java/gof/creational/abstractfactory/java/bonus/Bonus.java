package gof.creational.abstractfactory.java.bonus;

/*
https://learning.oreilly.com/videos/learn-design-patterns/9781788838795/9781788838795-video3_5

 */
interface Pizza {
  void getDescription();
  double getCost();
}
class VegPizza implements Pizza {
  @Override
  public void getDescription() {
    // TODO Auto-generated method stub
    System.out.println(" Veg Pizza from general Factory");
  }
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 10.1;
  }
}
class JainVegPizza implements Pizza {
  @Override
  public void getDescription() {
    // TODO Auto-generated method stub
    System.out.println(" Veg Pizza from Jain Factory");
  }
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 11.1;
  }
}
class CornPizza implements Pizza {
  @Override
  public void getDescription() {
    // TODO Auto-generated method stub
    System.out.println(" Corn Pizza from general Factory");
  }
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 20.1;
  }
}
class JainCornPizza implements Pizza {
  @Override
  public void getDescription() {
    // TODO Auto-generated method stub
    System.out.println(" Corn Pizza from Jain Factory");
  }
  @Override
  public double getCost() {
    // TODO Auto-generated method stub
    return 22.1;
  }
}
// Example of Abstract Factory Pattern
abstract class AbstracePizzaFactory {
  // Example of Factory method pattern
  public abstract Pizza createPizzaBase(String PizzaType);
  public Pizza getPizzaBase(String pizzaType) {
    return createPizzaBase(pizzaType);
  }
}
class JainPizzaFactory extends AbstracePizzaFactory{
  @Override
  public Pizza createPizzaBase(String pizzaType) {
    // Example of Simple Factory pattern
    Pizza pizza = null;
    if(pizzaType.equalsIgnoreCase("veg")){
      pizza = new JainVegPizza();
    }else
    if(pizzaType.equalsIgnoreCase("corn")){
      pizza = new JainCornPizza();
    }
    return pizza;
  }
}
class GeneralPizzaFactory extends AbstracePizzaFactory{
  @Override
  public Pizza createPizzaBase(String pizzaType) {
    Pizza pizza = null;
    if(pizzaType.equalsIgnoreCase("veg")){
      pizza = new VegPizza();
    }else
    if(pizzaType.equalsIgnoreCase("corn")){
      pizza = new CornPizza();
    }
    return pizza;
  }
}
class PizzaStore {
  public Pizza orderPizza(String pizzafactory, String pizzaType) {
    AbstracePizzaFactory pizzaFactory = getPizzaFactory(pizzafactory);
    Pizza pizza = pizzaFactory.getPizzaBase(pizzaType);
    bakePizza();
    return pizza;
  }
  private AbstracePizzaFactory getPizzaFactory(String pizzafactory) {
    if (pizzafactory.equalsIgnoreCase("Jain")) {
      return new JainPizzaFactory();
    } else if (pizzafactory.equalsIgnoreCase("General")) {
      return new GeneralPizzaFactory();
    }
    return new GeneralPizzaFactory();
  }
  private void bakePizza() {
    // TODO Auto-generated method stub
  }
}
public class Bonus {
  public static void main(String... args) {
    PizzaStore pizzaStore = new PizzaStore();
    Pizza pizza = pizzaStore.orderPizza("general", "veg");
    System.out.println("Your final order is");
    pizza.getDescription();
    System.out.println("Total cost of order is " + pizza.getCost());
  }
}
/*
Your final order is
 Veg Pizza from general Factory
Total cost of order is 10.1
 */
