package gof.creational.builder.java.bonus;

import java.util.ArrayList;
import java.util.List;

abstract class Item {
  Wrap warp;
  int cost = 2;
  String name;
}

class Burger extends Item {
}

class VegBurger extends Burger {
  public VegBurger() {
    this.name = "VegBurger";
  }
}

class ChikenBurger extends Burger {
  public ChikenBurger() {
    this.name = "ChikenBurger";
  }
}

class Wrap {
  public String name;
}

class Bottle extends Wrap {
  public Bottle() {
    this.name = "Bottle";
  }
}

class Box extends Wrap {
  public Box() {
    this.name = "Box";
  }
}

class Drink extends Item {
}

class Milk extends Drink {
  public Milk() {
    this.name = "Milk";
    cost = 10;
  }
}

class Blood extends Drink {
  public Blood() {
    this.name = "Blood";
  }
}

class Meal {
  int cost;
  List<Item> meal = new ArrayList<Item>();

  void addItem(Item item) {
    cost += item.cost;
    meal.add(item);
  }

  @Override
  public String toString() {
    System.out.println("You meal is ");
    for (Item itm : meal) {
      System.out.print(itm.name + " ");
    }
    System.out.println();
    System.out.println("Total cost - " + cost);
    return " ";
  }
}

class MealBuilder {

  public Meal buildVegMeal() {
    Meal meal = new Meal();
    Wrap bottle = new Bottle();
    Wrap box = new Box();
    VegBurger veg = new VegBurger();
    veg.warp = box;
    Drink milk = new Milk();
    milk.warp = bottle;

    meal.addItem(veg);
    meal.addItem(milk);
    return meal;
  }

  public Meal buildNonVegMeal() {
    Meal meal = new Meal();
    Wrap bottle = new Bottle();
    Wrap box = new Box();
    Burger veg = new ChikenBurger();
    veg.warp = box;
    Drink milk = new Blood();
    milk.warp = bottle;

    meal.addItem(veg);
    meal.addItem(milk);
    return meal;
  }
}

public class McDonaldsMealBuilderApp {
  public static void main(String[] args) {
    MealBuilder mealBuilder = new MealBuilder();
    Meal meal = mealBuilder.buildVegMeal();
    System.out.println(meal);
  }
}
/*
You meal is
VegBurger Milk
Total cost - 12
 */
