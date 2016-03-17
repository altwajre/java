package com.company.app.sender;

public class App
{
    private static final String REQUEST_QUEUE = "request_queue";

    public static void sendToRequestReplyQueue() {
        Sender sender = new Sender();
        sender.initialize();
        sender.sendRequest(REQUEST_QUEUE, "Test message.", "MSG1");
        // String result = sender.waitForResponse("MSG1");
        sender.destroy();
    }

    public static void main(String[] args) {
        sendToRequestReplyQueue();
    }
}
