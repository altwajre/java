package com.company.app.sender;

public class App
{
    public static void sendToDefaultExchange() {
        Sender sender = new Sender();
        sender.initialize();
        sender.send("Test message.");
        sender.destroy();
    }
    public static void main( String[] args )
    {
        sendToDefaultExchange();
    }
}
