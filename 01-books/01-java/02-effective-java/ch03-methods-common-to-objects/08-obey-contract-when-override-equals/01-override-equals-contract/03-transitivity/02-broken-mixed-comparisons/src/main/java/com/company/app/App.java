package com.company.app;

/*
Now p1.equals(p2) and p2.equals(p3) return true, while p1.equals(p3) returns false, a clear violation of transitivity.
The first two comparisons are “color-blind”, while the third takes color into account.
So what’s the solution? It turns out that this is a fundamental problem of equivalence relations in object-oriented
languages. There is no way to extend an instantiable class and add a value component while preserving the equals
contract, unless you are willing to forgo the benefits of object-oriented abstraction.
 */
enum Color{
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
class Point{
    private final int x;
    private final int y;
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override public boolean equals(Object o){
        if(!(o instanceof Point)) return false;
        Point p = (Point)o;
        return p.x == x && p.y == y;
    }
}
class ColorPoint extends Point{
    private final Color color;
    ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }
    // Broken - violates symmetry!
    @Override
    public boolean equals(Object o){ // mixed comparisons
        if(!(o instanceof Point)) return false;
        // If o is a normal Point, do a color-blind comparison
        if(!(o instanceof ColorPoint)) return o.equals(this);
        // o is a ColorPoint; do a full comparison
        return super.equals(o) && ((ColorPoint)o).color == color;
    }
}
public class App
{
    public static void main( String[] args )
    {
        // First equals function violates symmetry
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1,2,Color.RED);
        System.out.println(p.equals(cp) + " " + cp.equals(p)); // true true

        // Second equals function violates transitivity
        ColorPoint p1 = new ColorPoint(1,2,Color.RED);
        Point p2 = new Point(1,2);
        ColorPoint p3 = new ColorPoint(1,2,Color.BLUE);
        System.out.printf("%s %s %s%n",p1.equals(p2), p2.equals(p3), p1.equals(p3)); // true true false
    }
}
/*
output:
true true
true true false
 */
