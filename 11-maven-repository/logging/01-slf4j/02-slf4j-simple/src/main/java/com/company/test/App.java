package com.company.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App
{
    static Logger logger = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        logger.info("Hello from main");

        Customer customer = new Customer("Tom");
        customer.say("hi");
    }
}
/*
[main] INFO com.company.test.App - Hello from main
[main] INFO com.company.test.Customer - Tom say: hi
 */
