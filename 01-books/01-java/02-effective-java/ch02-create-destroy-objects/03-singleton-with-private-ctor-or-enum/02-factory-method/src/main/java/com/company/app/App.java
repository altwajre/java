package com.company.app;

/*
Singleton with public static factory method
1, keep the constructor private
2, exporting a public static method to provide access to the sole instance
Problem:
A privileged client can invoke the private constructor reflectively with the aid of the AccessibleObject.setAccessible
method. If you need to defend against this attack, modify the constructor to make it throw an exception if it’s asked
to create a second instance.
Advantage:
It gives you the flexibility to change your mind about whether the class should be a singleton without changing its API.
The factory method returns the sole instance but could easily be modified to return, say, a unique instance for each
thread that invokes it.
 */
class Elvis{
    private static final Elvis INSTANCE = new Elvis();
    private Elvis(){ }
    public static Elvis getInstance(){
        return INSTANCE;
    }
    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Elvis elvis = Elvis.getInstance();
        elvis.leaveTheBuilding();
    }
}
/*
output:
Whoa baby, I'm outta here!
 */