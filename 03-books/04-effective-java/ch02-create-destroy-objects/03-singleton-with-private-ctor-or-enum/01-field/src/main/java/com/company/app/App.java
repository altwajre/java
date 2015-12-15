package com.company.app;

/*
Singleton with public final field
1, keep the constructor private
2, exporting a public static member to provide access to the sole instance
Problem:
A privileged client can invoke the private constructor reflectively with the aid of the AccessibleObject.setAccessible
method. If you need to defend against this attack, modify the constructor to make it throw an exception if it’s asked
to create a second instance.
Advantage:
It makes clear that the class is a singlton: the public static field is final, so it will always contain the same
object reference.
 */
class Elvis{
    public static final Elvis INSTANCE = new Elvis(); // field
    private Elvis(){}
    public void leaveTheBuilding(){
        System.out.println("Whoa baby, I'm outta here!");
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
