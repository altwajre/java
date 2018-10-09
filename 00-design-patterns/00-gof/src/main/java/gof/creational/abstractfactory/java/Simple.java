package gof.creational.abstractfactory.java;

interface IceCream {
  Integer getCalories();

  Integer getCost();

  String getBrand();
}

class ChocolateIceCream implements IceCream {
  public ChocolateIceCream(String brand, Integer cost, Integer calories) {
    this.brand = brand;
    this.cost = cost;
    this.calories = calories;
  }

  String brand = "";
  Integer cost = 0;
  Integer calories = 0;

  @Override
  public Integer getCalories() {
    return calories;
  }

  @Override
  public Integer getCost() {
    return cost;
  }

  @Override
  public String getBrand() {
    return brand;
  }

  public String toString() {
    return this.getClass().getSimpleName() + "of Brand " + brand +
        " with Calories: " + getCalories() + " and cost: $" + getCost();
  }
}

class StrawberryIceCream implements IceCream {
  public StrawberryIceCream(String brand, Integer cost, Integer calories) {
    this.brand = brand;
    this.cost = cost;
    this.calories = calories;
  }

  String brand;
  Integer cost;
  Integer calories;

  @Override
  public Integer getCalories() {
    return calories;
  }

  @Override
  public Integer getCost() {
    return cost;
  }

  @Override
  public String getBrand() {
    return brand;
  }

  public String toString() {
    return this.getClass().getSimpleName() + " of Brand " + brand +
        " with Calories: " + getCalories() + " and cost: $" + getCost();
  }
}

interface MilkShake {
  Integer getIceAmount();

  String getBrand();
}

class ChocolateMilkShake implements MilkShake {
  String brand;
  Integer iceCubes;

  public ChocolateMilkShake(String brand, Integer iceCubes) {
    this.brand = brand;
    this.iceCubes = iceCubes;
  }

  @Override
  public Integer getIceAmount() {
    return this.iceCubes;
  }

  @Override
  public String getBrand() {
    return this.brand;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}

class StrawberryMilkShake implements MilkShake {
  String brand;
  Integer iceCubes;

  public StrawberryMilkShake(String brand, Integer iceCubes) {
    this.brand = brand;
    this.iceCubes = iceCubes;
  }

  @Override
  public Integer getIceAmount() {
    return this.iceCubes;
  }

  @Override
  public String getBrand() {
    return this.brand;
  }
}

abstract class AbstractIceCreamFactory {
  abstract IceCream createIceCream(String choice);

  abstract MilkShake createMilkShake(String choice);
}

class AmulIceCreamFactory extends AbstractIceCreamFactory {
  @Override
  public IceCream createIceCream(String iceCreamChoice) {
    IceCream iceCream = null;

    if (iceCreamChoice.equalsIgnoreCase("Strawberry")) {
      iceCream = new StrawberryIceCream("Amul", 2, 120);
    } else if (iceCreamChoice.equalsIgnoreCase("Chocolate")) {
      iceCream = new ChocolateIceCream("Amul", 2, 250);
    }

    return iceCream;
  }

  @Override
  MilkShake createMilkShake(String choice) {
    MilkShake milkShake = null;

    if (choice.equalsIgnoreCase("Strawberry")) {
      milkShake = new StrawberryMilkShake("Amul", 2);
    } else if (choice.equalsIgnoreCase("Chocolate")) {
      milkShake = new ChocolateMilkShake("Amul", 2);
    }
    return milkShake;
  }
}

class DairyQueenCreamFactory extends AbstractIceCreamFactory {

  @Override
  public IceCream createIceCream(String iceCreamChoice) {
    IceCream iceCream = null;

    if (iceCreamChoice.equalsIgnoreCase("Strawberry")) {
      iceCream = new StrawberryIceCream("Dairy Queen", 2, 120);
    } else if (iceCreamChoice.equalsIgnoreCase("Chocolate")) {
      iceCream = new ChocolateIceCream("Dairy Queen", 2, 250);
    }
    return iceCream;
  }

  @Override
  MilkShake createMilkShake(String choice) {
    MilkShake milkShake = null;

    if (choice.equalsIgnoreCase("Strawberry")) {
      milkShake = new StrawberryMilkShake("Dairy Queen", 2);
    } else if (choice.equalsIgnoreCase("Chocolate")) {
      milkShake = new ChocolateMilkShake("Dairy Queen", 2);
    }
    return milkShake;
  }
}

class FactoryCreator {
  static AbstractIceCreamFactory getFactory(String brand) {
    if (brand.equalsIgnoreCase("Amul")) {
      return new AmulIceCreamFactory();
    } else {
      return new DairyQueenCreamFactory();
    }
  }
}

public class Simple {
  public static void main(String[] args) {
    String choice = "Icecream";
    String flavor = "Strawberry";
    String brand = "Amul";

    AbstractIceCreamFactory factory = FactoryCreator.getFactory(brand);

    // Virtual constructor ( takes care of configuration )
    if (choice.equalsIgnoreCase("icecream")) {
      IceCream iceCream = factory.createIceCream(flavor);
      System.out.println(iceCream);
    } else {
      MilkShake milkShake = factory.createMilkShake(flavor);
      System.out.println(milkShake);
    }
  }
}
/*
StrawberryIceCream of Brand Amul with Calories: 120 and cost: $2
 */
