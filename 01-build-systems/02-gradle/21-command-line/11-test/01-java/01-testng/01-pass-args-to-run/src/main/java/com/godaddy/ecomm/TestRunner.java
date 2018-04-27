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

    static class TestOption {
        private boolean hasHelp;
        private String testngXmlPath;

        public TestOption() {
        }

        public TestOption(boolean hasHelp, String testngXmlPath) {
            this.hasHelp = hasHelp;
            this.testngXmlPath = testngXmlPath;
        }
    }

    public static void main(String... args) throws ParseException {
        System.out.println("#TestRunner.main()");
        TestOption testOption = new TestOption(false, "test-all.xml");

        parse(testOption, args);

        runTest(testOption);
    }

    static void parse(TestOption testOption, String... args) throws ParseException {
        Options options = new Options();
        String helpOpt = "h";
        String testngOpt = "t";
        options.addOption(helpOpt, "help", false, "display usage");
        options.addOption(testngOpt, "testng", true, "testng suite xml file");

        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine commandLine = commandLineParser.parse(options, args);
        if (commandLine.hasOption(helpOpt)) {
            new HelpFormatter().printHelp("ant", options);
            testOption.hasHelp = true;
        } else {
            if (commandLine.hasOption(testngOpt)) {
                testOption.testngXmlPath = commandLine.getOptionValue(testngOpt);
            }
        }
    }

    static void runTest(TestOption testOption) {
        if (testOption.hasHelp) return;

        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<>();
        suites.add(testOption.testngXmlPath);
        testNG.setTestSuites(suites);
        testNG.run();
    }
}
