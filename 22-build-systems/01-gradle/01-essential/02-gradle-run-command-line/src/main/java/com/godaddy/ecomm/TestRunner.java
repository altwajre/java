package com.godaddy.ecomm;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public final class TestRunner {
    static boolean hasHelp = false;
    static String testngXmlPath = "test-all.xml";

    public static void main(String... args) throws ParseException {
        parse(args);

        runTest();
    }

    static void parse(String... args) throws ParseException {
        Options options = new Options();
        String helpOpt = "h";
        String testngOpt = "t";
        options.addOption(helpOpt, "help", false, "display usage");
        options.addOption(testngOpt, "testng", true, "testng xml file");

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine commandLine = commandLineParser.parse(options, args);
        if (commandLine.hasOption(helpOpt)) {
            new HelpFormatter().printHelp("ant", options);
            hasHelp = true;
        } else {
            if (commandLine.hasOption(testngOpt)) {
                testngXmlPath = commandLine.getOptionValue(testngOpt);
            }
        }
    }

    static void runTest() {
        if(hasHelp) return;

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(testngXmlPath);
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
