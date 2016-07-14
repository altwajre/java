package com.company.app;

import java.util.logging.Logger;

public class App
{
    private static Logger globalLogger = Logger.getGlobal();
    private static Logger logger = Logger.getLogger(App.class.getPackage().getName());

    public static void main( String[] args )
    {
        globalLogger.fine("Global Logger fine");
        globalLogger.info("Global Logger info");
        globalLogger.severe("Global logger severe");

        logger.info("Trying to divide by zero");
        try{
            int result = 25/0;
        }catch (ArithmeticException e){
            logger.severe("Division by zero");
        }
    }
}
/*
output:
Jul 12, 2016 12:51:39 AM com.company.app.App main
INFO: Trying to divide by zero
Jul 12, 2016 12:51:39 AM com.company.app.App main
SEVERE: Division by zero
 */
