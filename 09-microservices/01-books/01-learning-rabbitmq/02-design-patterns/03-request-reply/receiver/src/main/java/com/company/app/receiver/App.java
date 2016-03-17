package com.company.app.receiver;

public class App
{
    public static void main( String[] args )
    {
        final RequestReceiver receiver = new RequestReceiver();
        receiver.initialize();
        receiver.receive();
        receiver.destroy();
    }
}
