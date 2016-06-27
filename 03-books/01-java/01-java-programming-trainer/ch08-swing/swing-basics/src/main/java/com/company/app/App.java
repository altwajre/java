package com.company.app;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {
        System.out.println(System.getProperty("user.dir"));

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add a button to the container
        JButton button = new JButton("Click me");
        window.add(button);
        window.setSize(200, 300);
        window.setTitle("Hello World");
        window.setVisible(true);
    }
}
