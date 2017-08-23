package com.company.app;

/*
While there is no satisfactory way to extend an instantiable class and add a value component, there is a fine workaround.
Follow “Favor composition over inheritance.” Instead of having ColorPoint extends Point, given ColorPoint a private Point
field and a public view method that returns the point at the same position at this color point
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
class ColorPoint {
    private final Point point;
    private final Color color;
    ColorPoint(int x, int y, Color color) {
        if(color == null) throw new NullPointerException();
        point = new Point(x, y);
        this.color = color;
    }
    // Returns the point-view of this color point.
    public Point asPoint(){
        return point;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof ColorPoint)) return false;
        ColorPoint cp = (ColorPoint)o;
        return cp.point.equals(point) && cp.color.equals(color);
    }
}
public class App
{
    public static void main( String[] args )
    {
        ColorPoint p1 = new ColorPoint(1,2,Color.RED);
        ColorPoint p2 = new ColorPoint(1,2,Color.RED);
        System.out.printf("%s %n", p1.equals(p2));
    }
}
/*
output:
true
 */