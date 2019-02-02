package com.company.app;

/*
Enum singleton - the preferred approach
1, it is equivalent to the public field appraoch
2, it provides the serialization machinery for free
3, it provides a guarantee against multiple instantiation, even facing serialization and reflection attacks.
 */
enum Elvis{
    INSTANCE;
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