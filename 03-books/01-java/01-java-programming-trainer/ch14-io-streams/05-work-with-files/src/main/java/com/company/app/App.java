package com.company.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class App
{
    public static void main( String[] args )
    {
//        fileTest();

        // Get the path to the file states.txt located in dir
        // this program was launched from
        Path sourceFilePath = Paths.get("states.txt");

        // Will copy the source file to this destination
        Path destFilePath = Paths.get("states.bak");

        if(Files.exists(sourceFilePath)) {
            System.out.println("The file " + sourceFilePath + " exists");
            System.out.println("The absolute path is " + sourceFilePath.toAbsolutePath());
            try{
                // Check the file size (in bytes)
                System.out.println("It's size is " + Files.size(sourceFilePath));

                // Copy the file from states.txt to the states.bak
                Files.copy(sourceFilePath, destFilePath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Copy completed. The backup file is at " + destFilePath.toAbsolutePath());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private static void fileTest() {
        System.out.println(Paths.get("pom.xml"));
        File file = new File("customers.txt");
        File backup = new File("customers.txt.bak");
        if(backup.exists()){
            System.out.println("delete backup");
            backup.delete();
        }
        file.renameTo(backup);
    }
}
/*
output:
The file states.txt exists
The absolute path is /Users/whan/05-work-with-files/states.txt
It's size is 38
Copy completed. The backup file is at /Users/whan/05-work-with-files/states.bak
 */