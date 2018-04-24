package com.company.app;

import java.util.HashSet;
import java.util.Set;

/*
http://www.programcreek.com/2013/09/java-hashcode-equals-contract-set-contains/
Java equals() and hashCode() Contract: http://www.programcreek.com/2011/07/java-equals-and-hashcode-contract/
The equals() contract is that if two objects are equal(by using equals() method), they must have the same hashCode. If
two objects have same hash code, they may be not equal.
 */
class Dog{
    private final String color;
    public Dog(String color) {
        this.color = color;
    }
    @Override
    public boolean equals(Object obj){
        System.out.println("#Dog.equals() is invoked");
        if(!(obj instanceof Dog)) return false;
        if(obj == this) return true;
        return this.color.equals(((Dog)obj).color);
    }
    public int hashCode(){
        System.out.println("#Dog.hashCode(): color.length="+color.length());
        return color.length();
    }
}
public class App
{
    public static void main( String[] args )
    {
        Set<Dog> dogSet = new HashSet<>();
        dogSet.add(new Dog("white"));
        dogSet.add(new Dog("white"));

        System.out.println("\nWe have " + dogSet.size() + " white dogs!");

        String temp = "Set.contains() ";
        if(dogSet.contains(new Dog("white"))){
            System.out.println(temp + "has a white dog!");
        }
        else{
            System.out.println(temp + "does NOT have white dog!");
        }
    }
}
/*
output:
#Dog.hashCode(): color.length=5
#Dog.hashCode(): color.length=5
#Dog.equals() is invoked

We have 1 white dogs!
#Dog.hashCode(): color.length=5
#Dog.equals() is invoked
Set.contains() has a white dog!
 */
