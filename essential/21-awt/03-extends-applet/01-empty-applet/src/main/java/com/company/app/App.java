package com.company.app;


import java.applet.Applet;
import java.awt.*;

class MyApplet extends Applet {

}
public class App
{
    public static void main( String[] args )
    {
        Frame frame = new Frame("MyApplet");
        MyApplet applet = new MyApplet();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
/*
A frame with an empty applet
 */
