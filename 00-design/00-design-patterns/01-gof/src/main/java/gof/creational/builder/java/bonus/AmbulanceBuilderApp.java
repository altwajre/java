package gof.creational.builder.java.bonus;

class VanFactory {
  // factory method
  Van createVan(int noOfWheel) {
    Van van = new Van();
    van.noOfWheels = noOfWheel;
    return van;
  }

  // simple factory
  Van getVan(int noOfWheel) {
    switch (noOfWheel) {
      case 1:
        return new FourWheelerVan();
      case 2:
        return new FourWheelerVan();
      default:
        return new EightWheelerVan();
    }
  }
}

class Van {
  int noOfWheels;
}

class SixWheelerVan extends Van {
  public SixWheelerVan() {
    noOfWheels = 6;
  }
}

class FourWheelerVan extends Van {
  public FourWheelerVan() {
    noOfWheels = 4;
  }
}

class EightWheelerVan extends Van {
  public EightWheelerVan() {
    noOfWheels = 8;
  }
}

class Ambulance {
  Van van;
  String label;

  Van getVan() {
    return van;
  }

  void setVan(Van van) {
    this.van = van;
  }

  String getLabel() {
    return label;
  }

  void setLabel(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return "Ambulance for " + label + " with number of wheels " + van.noOfWheels;
  }
}

class AmbulanceBuilder {
  Ambulance buildAmbulance(int numberOfWheel, String label) {
    Ambulance ambulance = new Ambulance();
    VanFactory vanFactory = new VanFactory();
    ambulance.setVan(vanFactory.getVan(numberOfWheel));
    ambulance.setLabel(label);
    return ambulance;
  }
}

public class AmbulanceBuilderApp {
  public static void main(String[] args) {
    int numberOfWheel = 1;
    String label = "my-label";

    AmbulanceBuilder ambulanceBuilder = new AmbulanceBuilder();
    Ambulance ambulance = ambulanceBuilder.buildAmbulance(numberOfWheel, label);

    System.out.println("Ambulance is build " + ambulance);
  }
}
/*
Ambulance is build Ambulance for my-label with number of wheels 4
 */
