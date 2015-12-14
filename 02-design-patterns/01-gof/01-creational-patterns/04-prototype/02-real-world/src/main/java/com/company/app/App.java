package com.company.app;
import java.util.HashMap;

// interface Cloneable is abstract Prototype
class Color implements Cloneable{  // Concrete Prototype
    private int _red;
    private int _green;
    private int _blue;
    public Color(int red, int green, int blue){
        this._red = red;
        this._green = green;
        this._blue = blue;
    }
    public Color clone(){
        System.out.format("Cloning color RGB: %s,%s,%s\n", _red, _green, _blue);
        try{
            return (Color) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    public String toString(){
        return "RGB: " + _red + "," + _green + "," + _blue;
    }
}
public class App
{
    public static void main( String[] args )
    {
        // Prototype Client - ColorManager
        HashMap<String, Cloneable> colorManager = new HashMap<String, Cloneable>();
        // standard colors
        colorManager.put("red", new Color(255, 0, 0));
        colorManager.put("green", new Color(0, 255, 0));
        colorManager.put("blue", new Color(0, 0, 255));
        // personalized colors
        colorManager.put("angry", new Color(255, 54, 0));
        colorManager.put("peace", new Color(128, 211, 128));
        colorManager.put("flame", new Color(211, 34, 20));

        Color clonedColor1 = ((Color) colorManager.get("red")).clone();
        System.out.println(clonedColor1);
        Color clonedColor2 = ((Color) colorManager.get("peace")).clone();
        System.out.println(clonedColor2);
        Color clonedColor3 = ((Color) colorManager.get("flame")).clone();
        System.out.println(clonedColor3);
    }
}
/*
Definition
Specify the kind of objects to create using a prototypical instance, and create new objects by copying this prototype.

output:
Cloning color RGB: 255,0,0
RGB: 255,0,0
Cloning color RGB: 128,211,128
RGB: 128,211,128
Cloning color RGB: 211,34,20
RGB: 211,34,20
 */
