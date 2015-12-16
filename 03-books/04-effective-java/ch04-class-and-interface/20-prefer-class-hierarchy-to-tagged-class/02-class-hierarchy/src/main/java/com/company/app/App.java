package com.company.app;

// Class hierarchy replacement for a tagged class
abstract class Figure{
    abstract double area();
}
class Circle extends Figure{
    final double radius;
    Circle(double radius) {
        this.radius = radius;
    }
    @Override
    double area() {
        return Math.PI * (radius * radius);
    }
}
class Rectangle extends Figure{
    final double length;
    final double width;
    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    @Override
    double area() {
        return length * width;
    }
}
class Square extends Rectangle{
    Square(double side) {
        super(side, side);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Square square = new Square(3);
        System.out.println(square.area());
    }
}
/*
output:
9.0
 */
