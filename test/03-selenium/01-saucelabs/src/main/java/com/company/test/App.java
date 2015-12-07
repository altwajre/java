package com.company.test;

import com.company.test.common.Configuration;
import com.company.test.common.Global;
import com.company.test.common.Platforms;
import org.apache.commons.cli.*;
import org.testng.TestNG;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App
{
    private static String configPath = "config.dev.yml";
    private static String testngXmlPath = "test-group.xml";
    private static boolean isHelp = false;
    public static void main( String[] args ) throws IOException {
        parseArgs(args);

        if(!isHelp) {
            initialize();
            runTest();
        }
    }

    static void parseArgs(String[] args) {
        Options options = new Options();

        options.addOption("c", "config", true, "config file");
        options.addOption("l", "logpath", false, "log path");
        options.addOption("t", "test", true, "testng xml file");
        options.addOption("h", "help", false, "display help message");

        CommandLineParser commandLineParser = new DefaultParser();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if(commandLine.hasOption("help")){
                new HelpFormatter().printHelp( "ant", options );
                isHelp = true;
            }
            else{
                if(commandLine.hasOption("c")){
                    String optValue = commandLine.getOptionValue("c");
                    if(optValue != null){
                        configPath = optValue;
                    }
                }
                if(commandLine.hasOption("t")){
                    String optValue = commandLine.getOptionValue("t");
                    if(optValue != null){
                        testngXmlPath = optValue;
                    }
                }
                if(commandLine.hasOption("l")){
                    Global.LogPath = true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            new HelpFormatter().printHelp( "ant", options );
        }
    }

    static void initialize() throws IOException {
        Yaml configYaml = new Yaml();
        InputStream in = Files.newInputStream(Paths.get(configPath));
        Configuration config = configYaml.loadAs(in, Configuration.class);
        Global.Config = config;
        System.out.println(Global.Config.getUrl());

        Yaml platYaml = new Yaml();
        in = Files.newInputStream(Paths.get("platforms.yml"));
        Platforms platforms = platYaml.loadAs(in, Platforms.class);
        Global.Platforms = platforms;
        System.out.println("platform data loaded");
    }

    static void runTest() {
        TestNG testNG = new TestNG();
        List<String> suites = new ArrayList<String>();
        suites.add(testngXmlPath);
        testNG.setTestSuites(suites);

        testNG.run();
    }
}
