package com.company.app;

interface AeroPlane {
  public static final String IS_A_CONSTANT = "constant";

  //	public abstract void goTo(int distance, int load);
//	public abstract void comeBack();
  default void goTo(int distance, int load) {
    System.out.println("AeroPlane.goTo()");
  }

  default void comeBack() {
    System.out.println("AeroPlane.comeBack()");
  }
}

interface FireFighterPlane extends AeroPlane {
  //	void dropWater();
//	void pickUpWater();
  default void dropWater() {
    System.out.println("FireFighterPlane.dropWater()");
  }

  default void pickUpWater() {
    System.out.println("FireFighterPlane.dropWater()");
  }
}

enum Cargo {
  PASSENGERS,
  CARGO,
  WATER
}

/*abstract*/ class CanadAir implements FireFighterPlane {
  private double consumption;
  private double autonomy;
  private int capacity;
  private Cargo cargo;

  public CanadAir(double con, double aut, int cap, Cargo water) {
    this.consumption = con;
    this.autonomy = aut;
    this.capacity = cap;
    this.cargo = water;
  }

  // Override default method
  @Override
  public void comeBack() {
    System.out.println("CanadAir.comeBack()");
  }

  public void stopFire() {
  }
}

public class App {
  public static void main(String[] args) {
    CanadAir caAir = new CanadAir(0.2, 200, 250, Cargo.WATER);

    System.out.println("caAir is-a AeroPlane " + (caAir instanceof AeroPlane));
    System.out.println("caAir is-a FireFighterPlane " + (caAir instanceof FireFighterPlane));
    System.out.println("caAir is-a CanadAir " + (caAir instanceof CanadAir));

    caAir.goTo(100, 150);
    caAir.comeBack();
    caAir.dropWater();
    caAir.stopFire();
  }
}
/*
caAir is-a AeroPlane true
caAir is-a FireFighterPlane true
caAir is-a CanadAir true
AeroPlane.goTo()
CanadAir.comeBack()
FireFighterPlane.dropWater()
 */
