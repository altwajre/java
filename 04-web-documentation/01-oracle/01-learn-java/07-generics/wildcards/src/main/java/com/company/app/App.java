package com.company.app;

import java.util.ArrayList;
import java.util.List;

/*
https://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html
 */
public class App 
{
    static class Canvas{
        public void draw(Shape s){
            s.draw(this);
        }
        public void drawAll(List<? extends Shape> shapes){ // this generic accepts list of any subclass of Shape.
            for(Shape s : shapes){
                s.draw(this);
            }
        }
    }
    static abstract class Shape{
        public abstract void draw(Canvas c);
    }
    static class Circle extends Shape{
        @Override
        public void draw(Canvas c) {
            System.out.println(this.getClass().getSimpleName());
        }
    }
    static class Rectangle extends Shape{
        @Override
        public void draw(Canvas c) {
            System.out.println(this.getClass().getSimpleName());
        }
    }
    public static void main( String[] args )
    {
        Canvas canvas = new Canvas();
        Circle circle = new Circle();
        List<Circle> shapes = new ArrayList<Circle>();
        shapes.add(circle);
        canvas.drawAll(shapes);
    }
}
