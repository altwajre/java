package gof.creational.factorymethod;

/*
Learn Design Patterns with Java

Factory Design Pattern
https://www.safaribooksonline.com/videos/learn-design-patterns/9781788838795/9781788838795-video3_4?autoplay=false
 */
interface IceCream {
  Integer getCalories();
  Integer getCost();
  String getIceCreamName();
}

class ChocolateIceCream implements IceCream {
  String brand = "";
  Integer cost = 0;
  Integer calories = 0;

  public ChocolateIceCream(Integer cost, Integer calories) {
    this.cost = cost;
    this.calories = calories;
  }

  @Override
  public Integer getCalories() {
    return calories;
  }

  @Override
  public Integer getCost() {
    return cost;
  }

  @Override
  public String getIceCreamName() {
    return this.getClass().getSimpleName();
  }

  @Override
  public String toString () {
    return this.getClass().getSimpleName() + " with Calories: " + getCalories() + " and cost: $" + getCost();
  }
}

class StrawberryIceCream implements IceCream {
  String brand = "";
  Integer cost = 0;
  Integer calories = 0;

  public StrawberryIceCream(Integer cost,Integer calories){
    this.cost =cost;
    this.calories =calories;
  }

  @Override
  public Integer getCalories () {
    return calories;
  }

  @Override
  public Integer getCost () {
    return cost;
  }

  @Override
  public String getIceCreamName () {
    return this.getClass().getSimpleName();
  }

  @Override
  public String toString () {
    return this.getClass().getSimpleName() + " with Calories: " + getCalories() + " and cost: $" + getCost();
  }
}
class IceCreamFactory {
  public static IceCream createIceCream(String iceCreamChoice) {
    IceCream iceCream = null;

    if(iceCreamChoice.equalsIgnoreCase("Strawberry")) {
      iceCream = new StrawberryIceCream(2, 120);
    }
    else if (iceCreamChoice.equalsIgnoreCase("Chocolate")) {
      iceCream = new ChocolateIceCream(2, 250);
    }
    return iceCream;
  }
}

public class Java {
  public static void main (String[] args) {
    IceCream iceCream = IceCreamFactory.createIceCream("Strawberry");
    System.out.println(iceCream);
  }
}
/*
StrawberryIceCream with Calories: 120 and cost: $2
 */
