import java.util.ArrayList;

/*
> Composition

- Composition implies a parent-child relationship
- The child cannot exist independent of the parent (i.e. a Part Object is intended to compose a Vehicle Object)
- The lifecycles of the involved objects are the same. If one if garbage collected the other will also be garbage collected

> Aggregation

- Aggregation implies a parent child relationship
- The child can exist independently of the parent (i.e. a Car Object may belong to a CarPool Object)
- The lifecycles of each of the involved Objects are independent

> Association

- A form of dependency (a reference to an Object)
- Both objects have independent lifecycles
 */
class Part {
  public boolean isFunctional = true;

  public void fix() {
    if (!isFunctional)
      isFunctional = true;
  }
}

class Vehicle {
  public double fuel;
  public double fuelCapacity;
  public double fuelConsumtion;
  public double mileage;
  public double lifespan;
  public ArrayList<Part> parts;

  public Vehicle(double lifespan, double mileage, double fuelCapacity, double fuelConsumtion, ArrayList<Part> parts) {
    this.mileage = mileage;
    this.lifespan = lifespan;
    this.fuelCapacity = fuelCapacity;
    this.fuelConsumtion = fuelConsumtion;
    this.parts = parts;
  }

  public void goToDestination(double miles) {
    this.mileage = this.mileage + miles;
    this.fuel = this.fuel - this.fuelConsumtion * miles;
  }
}

class Driver {
  private Vehicle availableVehicle;

  public void rentVehicle(Vehicle v) {
    this.availableVehicle = v;
    System.out.println("driver has now a vehicle to drive");
  }

  public void driveVehicle(double miles) {
    if (this.availableVehicle != null) {
      this.availableVehicle.goToDestination(miles);
      System.out.println("driver drives the vehicle safely");
    }
  }

  public void returnVehicle() {
    this.availableVehicle = null;
    System.out.println("driver returns the vehicle");
  }
}

public class App {
  public static void main(String[] args) {
    /*
     * Build cars (2 of them)>> Composition
     */
    ArrayList<Part> bMWParts = new ArrayList<Part>();
    Part bMWEngine = new Part();
    Part bMWBody = new Part();

    bMWParts.add(bMWEngine);
    bMWParts.add(bMWBody);

    Vehicle bmw = new Vehicle(1500, 500, 50, 0.7, bMWParts);

    ArrayList<Part> volvoParts = new ArrayList<Part>();
    Part volvoEngine = new Part();
    Part volvoBody = new Part();

    volvoParts.add(volvoEngine);
    volvoParts.add(volvoBody);

    Vehicle volvo = new Vehicle(1400, 300, 50, 0.6, volvoParts);

    /*
     * Build a vehicle pool >> Aggregation
     */
    ArrayList<Vehicle> vehiclePool1 = new ArrayList<Vehicle>();

    vehiclePool1.add(volvo);
    vehiclePool1.add(bmw);

    /*
     * Have a driver rent a car >> Association
     */
    Driver driver = new Driver();

    driver.rentVehicle(vehiclePool1.get(0));

    driver.driveVehicle(5);

    driver.returnVehicle();
  }
}
/*
driver has now a vehicle to drive
driver drives the vehicle safely
driver returns the vehicle
 */
