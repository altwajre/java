package com.company.app;

// Serializable singleton with public final field
class Elvis{
    public static final Elvis INSTANCE = new Elvis();
    private Elvis(){}
    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
    }
    private Object readResolve(){
        // Return the one true Elvis and let the garbage collector
        // take care of the Elvis impersonator
        return INSTANCE;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Elvis elvis = Elvis.INSTANCE;
        elvis.leaveTheBuilding();
    }
}
/*
output:
Whoa baby, I'm outta here!
 */