package com.company.app;

import java.awt.*;

class MyPanel extends Panel{
}
public class App
{
    public static void main( String[] args )
    {
        Frame frame = new Frame("MyPanel");
        MyPanel panel = new MyPanel();
        frame.add("Center", panel);
        frame.pack();
        frame.setVisible(true);
    }
}
/*
A frame with an empty panel
 */
