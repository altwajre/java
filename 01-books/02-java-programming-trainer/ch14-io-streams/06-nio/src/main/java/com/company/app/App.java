package com.company.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        bufferedReaderTest();

        readAllLinesTest();

        writerTest();
    }

    private static void writerTest() {
        System.out.println("# writerTest");
        Path outputFile = Paths.get("output.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8, StandardOpenOption.CREATE)){
            writer.write("Whatever you want to write");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void readAllLinesTest() {
        System.out.println("# readAllLinesTest");
        Path statesFile = Paths.get("states.txt");
        try{
            List<String> states = Files.readAllLines(statesFile, StandardCharsets.UTF_8);
            states.forEach(System.out::println);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void bufferedReaderTest() {
        System.out.println("# bufferedReaderTest");
        Path statesFile = Paths.get("states.txt");
        try(BufferedReader reader = Files.newBufferedReader(statesFile, StandardCharsets.UTF_8)){
            String stateName;
            while((stateName = reader.readLine()) != null) {
                System.out.println("Got the state " + stateName);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
/*
output:
# bufferedReaderTest
Got the state New York
Got the state New Jersey
Got the state Florida
Got the state California
# readAllLinesTest
New York
New Jersey
Florida
California
# writerTest

open output.txt
 */
