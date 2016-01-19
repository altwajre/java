package com.company.app;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyApplet extends Applet implements ActionListener{
    TextArea textArea = null;
    public void init(){
        setLayout(new BorderLayout());

        Panel panel1 = new Panel();
        textArea = new TextArea();
        panel1.add(textArea);
        add("Center", panel1);

        Panel panel2 = new Panel();
        Button one = new Button("One");
        one.addActionListener(this); // hookup the click event to actionPerformed() method
        panel2.add(one);
        panel2.add(new Button("Two"));

        Choice choice = new Choice();
        choice.addItem("one");
        choice.addItem("two");
        choice.addItem("three");

        panel2.add(choice);
        add("South", panel2);
    }

    // hookup all controls events
    public boolean action(Event e, Object o){
        String str = (String)o;
        textArea.appendText(str + "\n");
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        textArea.append(" #actionPerformed# ");
    }
}
public class App
{
    public static void main( String[] args )
    {
        Frame frame = new Frame("Event handling applet");
        MyApplet applet = new MyApplet();
        applet.init();
        frame.add("Center", applet);
        frame.pack();
        frame.setVisible(true);
    }
}
/*
An example with event handling
 */
