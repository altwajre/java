package com.company.test;

import org.apache.commons.cli.*;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cli {
    private static final Logger log = Logger.getLogger(Cli.class.getName());
    private String[] args = null;
    private Options options = new Options();

    public Cli(String[] args){
        this.args = args;
        options.addOption("h", "help", false, "show help.");
        options.addOption("v", "var", true, "Here you can set parameter.");
    }

    public void parse() throws ParseException {
        CommandLineParser parser = new BasicParser();
        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("h")){
            help();
        }
        if(cmd.hasOption("v")){
            log.log(Level.INFO, "Using cli argument -v=" + cmd.getOptionValue("v"));
        }
        else{
            log.log(Level.SEVERE, "Missing v option");
            help();
        }
    }

    private void help(){
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Main", options);
    }
}
