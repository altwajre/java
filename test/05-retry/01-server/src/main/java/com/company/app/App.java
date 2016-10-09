package com.company.app;

public class App
{
    // go to http://localhost:8080/topics
    public static void main( String[] args ) throws Exception {
        new DropwizardApp().run(new String[]{"server"});
    }
}
