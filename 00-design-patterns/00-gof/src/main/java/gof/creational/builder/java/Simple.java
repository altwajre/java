package gof.creational.builder.java;

import java.util.ArrayList;
import java.util.List;

class Room {
  private Integer numberOfWindows = 1;
  private Integer numberOfWalls = 4;

  public Integer getNumberOfWindows () {
    return numberOfWindows;
  }
  public void setNumberOfWindows (Integer numberOfWindows) {
    this.numberOfWindows = numberOfWindows;
  }
  public Integer getNumberOfWalls () {
    return numberOfWalls;
  }
  public void setNumberOfWalls (Integer numberOfWalls) {
    this.numberOfWalls = numberOfWalls;
  }
}
class BedRoom extends Room {
  public BedRoom(){
    setNumberOfWindows(2);
    setNumberOfWalls(4);
  }
}
class BathRoom extends Room {
  public BathRoom (){
    setNumberOfWindows(0);
    setNumberOfWalls(3);
  }
}
class LivingRoom extends Room {
  public LivingRoom (){
    setNumberOfWindows(2);
    setNumberOfWalls(3);
  }
}
class House {
  List<Room> rooms = new ArrayList<>();
  private String exteriorColor = "while";

  public String getExteriorColor () {
    return exteriorColor;
  }

  public void setExteriorColor (String exteriorColor) {
    this.exteriorColor = exteriorColor;
  }

  @Override
  public String toString () {
    System.out.println("house has total rooms " + rooms.size());
    System.out.println("house has color " + getExteriorColor());
    return "";
  }
}
abstract class HouseBuilder {
  public House getHouse () {
    return house;
  }

  private House house =null;

  public HouseBuilder(){
    house = new House();
  }

  public abstract void addRooms();

  public void addSecuritySystem(){
    System.out.println("adding security system");
  }

  public void addPlumbingSystem(){
    System.out.println("adding plumbing system");
  }

  public void addAirConditionerSystem(){
    System.out.println("adding air conditioning system");
  }

  public void paintHouse(String color){
    System.out.println("painting house with color " + color);
    house.setExteriorColor(color);
  }
}
class OneBedroomHouseBuilder extends HouseBuilder{
  @Override
  public void addRooms () {
    getHouse().rooms.add(new BathRoom());
    getHouse().rooms.add(new BedRoom());
    getHouse().rooms.add(new LivingRoom());
  }
}
class TwoBedroomHouseBuilder extends HouseBuilder{
  @Override
  public void addRooms () {
    getHouse().rooms.add(new BathRoom());
    getHouse().rooms.add(new BedRoom());
    getHouse().rooms.add(new BedRoom());
    getHouse().rooms.add(new LivingRoom());
  }
}
class Architect {
  private HouseBuilder houseBuilder;

  public void setHouseBuilder (HouseBuilder houseBuilder) {
    this.houseBuilder = houseBuilder;
  }

  public void buildHouse() {
    houseBuilder.addRooms();
    houseBuilder.addPlumbingSystem();
    houseBuilder.addAirConditionerSystem();
    houseBuilder.addSecuritySystem();
    houseBuilder.paintHouse("white");
  }

  public House getFinishedHouse(){
    return houseBuilder.getHouse();
  }
}

public class Simple {
  public static void main (String[] args) {
    System.out.println("Builder Design pattern example ");
    Architect architect = new Architect();

    // Hiding complexity and building one bed room house
    architect.setHouseBuilder(new TwoBedroomHouseBuilder());
    architect.buildHouse();
    House house = architect.getFinishedHouse();
    System.out.println(house);

  }
}
/*
Builder Design pattern example
adding plumbing system
adding air conditioning system
adding security system
painting house with color white
house has total rooms 4
house has color white
 */
