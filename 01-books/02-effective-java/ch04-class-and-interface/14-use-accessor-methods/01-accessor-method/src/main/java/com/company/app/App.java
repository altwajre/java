package com.company.app;

// Encapsulation of data by accessor methods and mutators
class Point{
    private double x;
    private double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public void setX(double x){
        this.x = x;
    }
    public double getY(){
        return y;
    }
    public void setY(double y){
        this.y = y;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Point point = new Point(1, 2);
        System.out.println(point.getX());
    }
}
/*
output:
1.0
 */
