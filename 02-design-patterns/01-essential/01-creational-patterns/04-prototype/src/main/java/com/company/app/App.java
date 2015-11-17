package com.company.app;
import java.util.HashMap;
public class App
{
    // interface Cloneable is abstract Prototype
    static class Color implements Cloneable{  // Concrete Prototype
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
