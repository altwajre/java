package com.company.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class App {
  public static void main(String[] args) {
    Config defaultConfig = ConfigFactory.parseResources("defaults.conf");
    Config fallbackConfig = ConfigFactory.parseResources("overrides.conf")
        .withFallback(defaultConfig)
        .resolve();

    System.out.println("# get from defaultConfig");
    System.out.println(defaultConfig.getString("conf.name"));
    System.out.println(defaultConfig.getString("conf.title"));

    System.out.println("# get from fallbackConfig");
    System.out.println(fallbackConfig.getString("conf.name"));
    System.out.println(fallbackConfig.getString("conf.title"));
  }
}
/*
# get from defaultConfig
default
Simple Title
# get from fallbackConfig
overrides
Simple Title
 */
