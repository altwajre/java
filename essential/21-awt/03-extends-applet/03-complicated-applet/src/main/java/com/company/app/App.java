package com.company.app;

import java.applet.Applet;
import java.awt.*;

class MyApplet extends Applet{
    public void init(){
        setLayout(new BorderLayout());

        Panel panel1 = new Panel();
        panel1.add(new TextArea());
        add("Center", panel1);

        Panel panel2 = new Panel();
        panel2.add(new Button("One"));
        panel2.add(new Button("Two"));
        Choice choice = new Choice();
        choice.addItem("one");
        choice.addItem("two");
        choice.addItem("three");
        panel2.add(choice);
        add("South", panel2);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Frame frame = new Frame("Complicated applet");
        MyApplet applet = new MyApplet();
        applet.init();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
/*
A more complicated example
 */
