package com.company.app;

import java.applet.Applet;
import java.awt.*;

class MyApplet extends Applet{
    @Override
    public void init(){
        add(new Button("One"));
        add(new Button("Two"));
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(200, 100);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Frame frame = new Frame("Applet has buttons");
        MyApplet applet = new MyApplet();
        applet.init();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
/*
An applet with two buttons
 */
