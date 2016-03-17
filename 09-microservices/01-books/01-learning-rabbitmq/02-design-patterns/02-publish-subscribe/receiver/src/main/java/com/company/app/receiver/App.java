package com.company.app.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App
{
    public static void main( String[] args ) throws InterruptedException {
        final PublishSubscribeReceiver receiver1 = new PublishSubscribeReceiver();
        receiver1.initialize();
        final PublishSubscribeReceiver receiver2 = new PublishSubscribeReceiver();
        receiver2.initialize();

        final Logger logger = LoggerFactory.getLogger(App.class);
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                String message = receiver1.receive("pubsub_queue1");
                logger.info("receiver1: "+message);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                String message = receiver2.receive("pubsub_queue2");
                logger.info("receiver2: "+message);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        receiver1.destroy();
        receiver2.destroy();
    }
}
