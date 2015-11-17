package com.company.app;

import java.util.HashMap;

abstract class VehicleBuilder{
    protected Vehicle vehicle;
    public Vehicle getVehicle(){
        return vehicle;
    }
    public abstract void buildFrame();
    public abstract void buildEngine();
    public abstract void buildWheels();
    public abstract void buildDoors();
}
class MotorCycleBuilder extends VehicleBuilder{
    public MotorCycleBuilder(){
        vehicle = new Vehicle("MotorCycle");
    }
    @Override
    public void buildFrame() {
        vehicle.setPart("frame", "MotorCycle Frame");
    }
    @Override
    public void buildEngine() {
        vehicle.setPart("engine", "500 cc");
    }
    @Override
    public void buildWheels() {
        vehicle.setPart("wheels", "2");
    }
    @Override
    public void buildDoors() {
        vehicle.setPart("doors", "0");
    }
}
class CarBuilder extends VehicleBuilder{
    public CarBuilder(){
        vehicle = new Vehicle("Car");
    }
    @Override
    public void buildFrame() {
        vehicle.setPart("frame", "Car Frame");
    }
    @Override
    public void buildEngine() {
        vehicle.setPart("engine", "2500 cc");
    }
    @Override
    public void buildWheels() {
        vehicle.setPart("wheels", "4");
    }
    @Override
    public void buildDoors() {
        vehicle.setPart("doors", "4");
    }
}
class ScooterBuilder extends VehicleBuilder{
    public ScooterBuilder(){
        vehicle = new Vehicle("Scooter");
    }
    @Override
    public void buildFrame() {
        vehicle.setPart("frame", "Scooter Frame");
    }
    @Override
    public void buildEngine() {
        vehicle.setPart("engine", "50 cc");
    }
    @Override
    public void buildWheels() {
        vehicle.setPart("wheels", "2");
    }
    @Override
    public void buildDoors() {
        vehicle.setPart("doors", "0");
    }
}
class Vehicle{
    private String vehicleType;
    private HashMap<String, String> parts = new HashMap<String, String>();
    public Vehicle(String vehicleType){
        this.vehicleType = vehicleType;
    }
    public String getPart(String key){
        return parts.get(key);
    }
    public void setPart(String key, String value){
        parts.put(key, value);
    }
    public void show(){
        System.out.println("\n--------------------");
        System.out.printf("Vehicle Type: %s\n", vehicleType);
        System.out.printf(" Frame   : %s", parts.get("frame"));
        System.out.printf(" Engine  : %s", parts.get("engine"));
        System.out.printf(" #Wheels : %s", parts.get("wheels"));
        System.out.printf(" #Doors  : %s", parts.get("doors"));
    }
}
// Director
class Shop{
    public void construct(VehicleBuilder vehicleBuilder){
        vehicleBuilder.buildFrame();
        vehicleBuilder.buildEngine();
        vehicleBuilder.buildWheels();
        vehicleBuilder.buildDoors();
    }
}
// Builder
public class App
{
    public static void main( String[] args )
    {
        VehicleBuilder builder;
        Shop shop = new Shop();

        builder = new ScooterBuilder();
        shop.construct(builder);
        builder.getVehicle().show();

        builder = new CarBuilder();
        shop.construct(builder);
        builder.getVehicle().show();

        builder = new MotorCycleBuilder();
        shop.construct(builder);
        builder.getVehicle().show();
    }
}
/*
output:
--------------------
Vehicle Type: Scooter
 Frame   : Scooter Frame Engine  : 50 cc #Wheels : 2 #Doors  : 0
--------------------
Vehicle Type: Car
 Frame   : Car Frame Engine  : 2500 cc #Wheels : 4 #Doors  : 4
--------------------
Vehicle Type: MotorCycle
 Frame   : MotorCycle Frame Engine  : 500 cc #Wheels : 2 #Doors  : 0
 */
