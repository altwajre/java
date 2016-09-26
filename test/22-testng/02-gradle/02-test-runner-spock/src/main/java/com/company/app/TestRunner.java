package com.company.app;

import com.godaddy.logging.Logger;
import com.google.common.base.Strings;
import org.apache.commons.cli.*;
import org.testng.TestNG;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.godaddy.logging.LoggerFactory.getLogger;

public class TestRunner {
  private static final Logger logger = getLogger(TestRunner.class);

  public static void main(String... args) {
    Global.EnvConfigPath = "config.test.yml";
    Global.TestGroupPath = "test-all.xml";

    try {
      ArgsParser.parse(args);
    } catch (ParseException e) {
      e.printStackTrace();
      throw new RuntimeException("Error: failed to parser args");
    }

    if (ArgsParser.hasHelp) {
      return;
    }

    try {
      ConfigInitializer.initialize();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Error: failed to initialize config");
    }

    runTest();
  }

  static void runTest() {
    TestNG testNG = new TestNG();
    List<String> suites = new ArrayList<>();
    suites.add(Global.TestGroupPath);
    testNG.setTestSuites(suites);
    testNG.run();
  }

  static class ArgsParser {
    static boolean hasHelp = false;

    static void parse(String... args) throws ParseException {
      logger.info("Parse command line arguments");

      Options options = new Options();

      String configOpt = "c";
      String helpOpt = "h";
      String testOpt = "t";

      options.addOption(configOpt, "config", true, "env config file");
      options.addOption(helpOpt, "help", false, "display usage");
      options.addOption(testOpt, "test", true, "testng xml file");

      CommandLineParser commandLineParser = new DefaultParser();

      CommandLine commandLine = commandLineParser.parse(options, args);
      if (commandLine.hasOption(helpOpt)) {
        new HelpFormatter().printHelp("ant", options);
        hasHelp = true;
      } else {
        if(commandLine.hasOption(configOpt)) {
          String optValue = commandLine.getOptionValue(configOpt);
          if(optValue != null){
            Global.EnvConfigPath = optValue;
          }
        }
        if (commandLine.hasOption(testOpt)) {
          String optValue = commandLine.getOptionValue(testOpt);
          if (optValue != null) {
            Global.TestGroupPath = optValue;
          }
        }
      }
    }
  }

  static class ConfigInitializer {
    private static <T> T loadYaml(String path, Class type) throws IOException {
      Yaml configYaml = new Yaml();
      InputStream in = Files.newInputStream(Paths.get(path));
      return (T)configYaml.loadAs(in, type);
    }

    static void initialize() throws IOException {
      if(Strings.isNullOrEmpty(Global.EnvConfigPath)){
        throw new RuntimeException("Error: Global.EnvConfigPath cannot be null or empty");
      }

      Global.Config = loadYaml(Global.EnvConfigPath, Configuration.class);
    }
  }
}
