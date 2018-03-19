package com.company.app;

/*
use this() to call another constructor
 */

class Bus {
  int noOfSeats;
  String busColor;

  public Bus() {
    this(28); // Using another constructor and proceeding with default values..
  }

  public Bus(int seats) {
    this(seats, "red"); // Using another constructor and proceeding..
  }

  public Bus(int seats, String color) {
    this.noOfSeats = seats;
    this.busColor = color;
  }
}

public class App {
  public static void main(String[] args) {
    Bus bus = new Bus();
    System.out.println("Seats=" + bus.noOfSeats);
  }
}
/*
Seats=28
 */
