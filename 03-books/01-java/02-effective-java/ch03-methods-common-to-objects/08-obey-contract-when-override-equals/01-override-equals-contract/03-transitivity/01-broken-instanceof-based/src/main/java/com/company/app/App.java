package com.company.app;

/*
instanceof based equals
The problem with this method is that you might get different results when comparing a point to a color point and
vice verse. The former comparison ignores color, while the latter comparison always return false because the type of
the argument is incorrect.
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
    public boolean equals(Object o){
        if(!(o instanceof ColorPoint)) return false;
        return super.equals(o) && ((ColorPoint)o).color == color; // violates symmetry!
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
        System.out.printf("%s %s %s%n",p1.equals(p2), p2.equals(p3), p1.equals(p3));
    }
}
/*
output:
true false
false true false
 */
