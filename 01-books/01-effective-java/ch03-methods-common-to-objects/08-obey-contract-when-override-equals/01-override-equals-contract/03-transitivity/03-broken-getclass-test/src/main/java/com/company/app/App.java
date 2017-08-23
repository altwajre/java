package com.company.app;

/*
false when compare ColorPoint.equals(Point) because o.getClass() != this.getClass()
 */
enum Color{
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}
class Point{
    protected final int x;
    protected final int y;
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
    // Broken - violates Liskov substitution principle
    @Override
    public boolean equals(Object o){
        System.out.println(o.getClass());
        System.out.println(this.getClass());
        if(o == null || o.getClass() != this.getClass()) return false;
        Point p = (Point) o;
        return p.x == this.x && p.y == this.y;
    }
}
public class App
{
    public static void main( String[] args )
    {
        // First equals function violates symmetry
        Point p = new Point(1, 2);
        ColorPoint cp = new ColorPoint(1,2,Color.RED);
        System.out.println(p.equals(cp) + " " + cp.equals(p)); // true false

        // Second equals function violates transitivity
        ColorPoint p1 = new ColorPoint(1,2,Color.RED);
        Point p2 = new Point(1,2);
        ColorPoint p3 = new ColorPoint(1,2,Color.BLUE);
        System.out.printf("%s %s %s%n",p1.equals(p2), p2.equals(p3), p1.equals(p3)); // false true true
    }
}
/*
output:
true false
false true true
 */
