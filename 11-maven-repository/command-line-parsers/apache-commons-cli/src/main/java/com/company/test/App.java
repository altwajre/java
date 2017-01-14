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

        options.addOption("p", "print", true, "print message");

        CommandLineParser parser = new DefaultParser();

        try{
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption("t")) {
                System.out.println("out.print date and time");
            }
            else if(cmd.hasOption("p")){
                String msg = cmd.getOptionValue("p");
                if(msg == null){
                    System.out.println("no msg");
                }
                else{
                    System.out.println(msg);
                }
            }
            else if(cmd.hasOption("help")){
                new HelpFormatter().printHelp( "ant", options );
            }
            else{
                System.out.println("out.print default behavior");
            }
        }
        catch (Exception e){
            System.out.println(e);
            new HelpFormatter().printHelp( "ant", options );
        }
    }
}
