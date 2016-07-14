package com.company.app;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App
{
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        FileHandler fileHandler;
        try{
            fileHandler = new FileHandler("logs/helloWorld.log");
            logger.addHandler(fileHandler);
//            fileHandler.setLevel(Level.WARNING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.fine("Hello from the fine world");
        logger.info("Trying to divide by zero");
        try{
            int result = 25/0;
        }catch (Exception e){
            logger.severe("Division by zero");
        }
    }
}
