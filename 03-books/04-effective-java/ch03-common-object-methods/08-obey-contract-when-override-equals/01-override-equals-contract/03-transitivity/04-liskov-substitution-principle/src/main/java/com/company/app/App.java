package com.company.app;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/*
The Liskov substitution principle says that any important property of a type should also hold for its subtypes, so that
any method written for the type should work equally well on its subtypes. But suppose we pass a CounterPoint instance to
the onUnitCircle method. If the Point class uses a getClass based equals method, the onUnitCircle method will return
false regardless of the CounterPoint instance's x and y values. If however, you use a proper instanceof-based equals
method on Point, the same onUnitCircle method will work fine when presented with a CounterPoint.

For Set.contains() method to work, need to override equals and hashCode methods.
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
class CounterPoint extends Point{
    private static final AtomicInteger counter = new AtomicInteger();
    CounterPoint(int x, int y) {
        super(x, y);
        counter.incrementAndGet(); // keep track of how many instances have been created
    }
    public int numberCreated(){
        return counter.get();
    }
}
public class App
{
    // Initialize UnitCircle to contain all Points on the unit circle
    private static final Set<Point> unitCircle;
    static {
        unitCircle = new HashSet<Point>();
        unitCircle.add(new Point(1,0));
        unitCircle.add(new Point(0,1));
        unitCircle.add(new Point(-1,0));
        unitCircle.add(new Point(0,-1));
    }
    public static boolean onUnitCircle(Point p){
        return unitCircle.contains(p);
    }
    public static void main( String[] args )
    {
        Point p1 = new Point(1,0);
        Point p2 = new CounterPoint(1,0);
        System.out.println(onUnitCircle(p1));
        System.out.println(onUnitCircle(p2));
    }
}
/*
output:
true
true
 */
