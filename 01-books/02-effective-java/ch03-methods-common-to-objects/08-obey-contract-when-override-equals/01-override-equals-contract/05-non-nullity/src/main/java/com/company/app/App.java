package com.company.app;

/*
“Non-nullity” the final requirement, which in the absence of a name I have taken the liberty of calling “non-nullity”
says that all objects must be unequal to null. While it is hard to imagine accidentally returning true in response to
the invocation o.equals(null), it isn’t hard to imagine accidentally throwing a NullPointerException. The general
contract does not allow this. Many classes have equals methods that guard against this with an explicit test for null.

This test is unnecessary. To test its argument for equality, the equals method must first cast its argument to an
appropriate type so its accessors may be invoked or its fields accessed. Before doing the cast, the method must use
the instanceof operator to check that its argument is of the correct type.

If this type check were missing and the equals method were passed an argument of the wrong type, the equals method
would throw a ClassCastException, which violates the equals contract, But the instanceof operator is specified to return
false if its first operand is null, regardless of what type appears in the second operand. Therefore the type check will
return false if null is passed in, so you don’t need a separate null check.
 */
class Point{
    private final int x;
    private final int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override public boolean equals(Object o){
        if(!(o instanceof Point)) return false;
        if(o == this) return true;
        Point p = (Point)o;
        return p.x == this.x && p.y == this.y ;
    }
    @Override
    public int hashCode(){
        return x + y;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Point p1 = new Point(1,0);
        Point p2 = new Point(1,0);
        System.out.println("p1.equals(p2)="+p1.equals(p2));
        System.out.println("p2.equals(p1)="+p2.equals(p1));
    }
}
/*
output:
p1.equals(p2)=true
p2.equals(p1)=true
 */