package gof.structural.flyweight.java;

import java.util.HashMap;
import java.util.Map;

interface Star {
  void displayBrightness();
}

class BrightStar implements Star {

  private String brightness;

  public BrightStar() {
    this.brightness = "bright";
  }

  @Override
  public void displayBrightness() {
    System.out.println("Star with brightness :" + brightness);
  }

  @Override
  public String toString() {
    return "Star with brightness :" + brightness;
  }
}

class DimStar implements Star {

  private String brightness;

  public DimStar() {
    this.brightness = "dim";
  }

  @Override
  public void displayBrightness() {
    System.out.println("Star with brightness :" + brightness);
  }

  @Override
  public String toString() {
    return "Star with brightness :" + brightness;
  }
}

class DullStar implements Star {

  private String brightness;

  public DullStar() {
    this.brightness = "dull";
  }

  @Override
  public void displayBrightness() {
    System.out.println("Star with brightness :" + brightness);
  }

  @Override
  public String toString() {
    return "Star with brightness :" + brightness;
  }
}

class StarFactory {
  static Map<String, Star> starCache = new HashMap<>();

  public static Star getStar(String brightnessLevel) {

    // First try to retrieve object from cache
    Star star = starCache.get(brightnessLevel);

    // if star does not exist in cache then factory will create one and store it in cache
    if (star == null) {
      if (brightnessLevel.equalsIgnoreCase("bright")) {
        star = new BrightStar();
        starCache.put("bright", star);
      }
      if (brightnessLevel.equalsIgnoreCase("dim")) {
        star = new DimStar();
        starCache.put("dim", star);
      }
      if (brightnessLevel.equalsIgnoreCase("dull")) {
        star = new DullStar();
        starCache.put("dull", star);
      }

    }
    // return star object for reuse
    return star;
  }
}

class Landscape {
  public void displayStar(Star star, int xCoord, int yCoord) {
    System.out.println("logic to display a " + star + " at X / Y coordinates ");
  }
}

public class Base {
  public static void main(String[] args) {
    System.out.println("Flyweight design pattern using ** STAR WARS **  example");

    // Create Landscape object which is responsible to display stars
    Landscape landscape = new Landscape();

    // Factory will provide star based on param
    // Note: Factory will cache objects for reuse
    // and will create new object only when it does not exist in cache.
    Star star = StarFactory.getStar("dull");

    landscape.displayStar(star, 65, 87);

    // No new object needs to be created, Factory provides reusable object
    // from cache which landscape displays
    landscape.displayStar(StarFactory.getStar("bright"), 34, 43);
    landscape.displayStar(StarFactory.getStar("bright"), 36, 47);
    landscape.displayStar(StarFactory.getStar("dull"), 34, 43);
    landscape.displayStar(StarFactory.getStar("dim"), 34, 43);
    landscape.displayStar(StarFactory.getStar("dim"), 34, 43);
  }
}
/*
Flyweight design pattern using ** STAR WARS **  example
logic to display a Star with brightness :dull at X / Y coordinates
logic to display a Star with brightness :bright at X / Y coordinates
logic to display a Star with brightness :bright at X / Y coordinates
logic to display a Star with brightness :dull at X / Y coordinates
logic to display a Star with brightness :dim at X / Y coordinates
logic to display a Star with brightness :dim at X / Y coordinates
 */
