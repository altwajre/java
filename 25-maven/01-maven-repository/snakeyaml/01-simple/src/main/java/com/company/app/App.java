package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App
{
    public static void main( String[] args ) throws IOException {
        Yaml yaml = new Yaml();
        InputStream inputStream = Files.newInputStream(Paths.get("config.yml"));
        Configuration configuration = yaml.loadAs(inputStream, Configuration.class);
        System.out.println(configuration.getMessage());
    }
}
/*
output:
hello
 */