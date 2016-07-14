package com.company.app;

import java.util.logging.Logger;

public class App
{
    private static Logger logger = Logger.getGlobal();
    public static void main( String[] args )
    {
        logger.fine("Hello fine world!");
        logger.info("Hello info world!");
        logger.severe("Hello severe world!");
    }
}
/*
output:
Jul 12, 2016 12:38:13 AM com.company.app.App main
INFO: Hello info world!
Jul 12, 2016 12:38:13 AM com.company.app.App main
SEVERE: Hello severe world!
 */
