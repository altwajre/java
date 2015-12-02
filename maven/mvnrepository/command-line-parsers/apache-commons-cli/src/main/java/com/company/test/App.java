package com.company.test;

import org.apache.commons.cli.*;

public class App
{
    public static void main( String[] args ) {
        // create Options object
        Options options = new Options();

        Option help = new Option( "help", "print help message" );
        options.addOption(help);
        // add t option
        options.addOption("t", false, "display current time");

        CommandLineParser parser = new DefaultParser();

        try{
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("t")) {
                System.out.println("date and time");
            }
            else if(cmd.hasOption("help")){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "ant", options );
            }
            else{
                System.out.println("default behavior");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
