package com.company.app;

public class App
{
    public static void main( String[] args ) throws Exception {
        new DropwizardApp().run(new String[]{"server"});
    }
}
