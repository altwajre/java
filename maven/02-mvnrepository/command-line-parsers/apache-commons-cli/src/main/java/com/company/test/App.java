package com.company.test;

import org.apache.commons.cli.*;

public class App
{
    public static void main( String[] args ) {
        // create Options object
        Options options = new Options();

        // add t option
        options.addOption("t", false, "display current time");

        CommandLineParser parser = new DefaultParser();

        try{
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("t")) {
                System.out.println("date and time");
            }
            else{
                System.out.println("date");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
