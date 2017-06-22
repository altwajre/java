package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws Exception {

        SequenceOfScalars();

        System.out.println( "Hello World!" );
    }

    private static void SequenceOfScalars() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("src/main/resources/spec/01-sequence-of-scalars.yaml"));
        Yaml yaml = new Yaml();
        Object load = yaml.load(inputStream);
        System.out.println(load);
    }
    /*
    output:
    [Mark McGwire, Sammy Sosa, Ken Griffey]
     */
}
