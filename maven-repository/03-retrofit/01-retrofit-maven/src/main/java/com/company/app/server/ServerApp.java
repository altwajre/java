package com.company.app.server;

public class ServerApp
{
    public static void main( String[] args ) throws Exception {
        new DropwizardApp().run(new String[]{"server"});
    }
}
