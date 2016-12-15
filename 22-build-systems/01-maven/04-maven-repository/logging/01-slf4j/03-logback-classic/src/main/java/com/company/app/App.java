package com.company.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App
{
    static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        logger.info("# Logged info");

        System.out.println("Console write");
    }
}
/*
https://www.youtube.com/watch?v=tMLEbGJ2z7I&hd=1;

output:
15:53:46.955 [main] INFO  com.company.app.App - # Logged info
Console write
 */
