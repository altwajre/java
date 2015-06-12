package com.company.test;

import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ParseException {
        new Cli(args).parse();
        System.out.println("Done");
    }
}
