package com.company.app;

/*
default method rules:
1, Subtypes automatically carry over the default methods from their supertypes
2, For interfaces that contribute a default method, the implementation in a subtype takes precedence over the one in supertypes
3, Implementations in classes, including abstract declarations, take precedence over all interface defaults
4, If there's a conflict between two or more default method implementations, or there's a default-abstract conflict between
  two interfaces, the inheriting class should disambiguate.

 */
interface Fly{
    default void takeOff(){ System.out.println("Fly::takeOff"); }
    default void land() {System.out.println("Fly::land");}
    default void turn() {System.out.println("Fly::turn");}
    default void cruise() {System.out.println("Fly::cruise");}
}
// FastFly extends Fly, overrides the takesOff()
// FastFly carries forward three methods of the Fly
//
interface FastFly extends Fly{
    default void takeOff(){System.out.println("FastFly::takeOff");}
}
interface Sail{
    default void cruise() {System.out.println("Sail::cruise");}
    default void turn() {System.out.println("Sail::turn");}
}
class Vehicle{
    public void turn() {System.out.println("Vehicle::turn");}
}
/*
1, the superclass Vehicle takes precedence on turn() even turn() is present in the interfaces
2, compile will force SeaPlane to implement cruise() because FastFly and Sail both implement cruise() causing conflict
 */
class SeaPlane extends Vehicle implements FastFly, Sail{
    private int altitude;
    public void cruise(){
        System.out.println("SeaPlane::cruise currently cruise like: ");
        if(altitude > 0){
            FastFly.super.cruise();
        }
        else{
            Sail.super.cruise();
        }
    }
}
public class App
{
    public static void main( String[] args )
    {
        SeaPlane seaPlane = new SeaPlane();
        seaPlane.takeOff();
        seaPlane.turn();
        seaPlane.cruise();
        seaPlane.land();
    }
}
/*
output:
FastFly::takeOff
Vehicle::turn
SeaPlane::cruise currently cruise like:
Sail::cruise
Fly::land
 */

