package com.company.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App
{
    public static void main( String[] args ) throws IOException {
        System.out.println("#list all files");
        Files.list(Paths.get("."))
                .forEach(System.out::println);

        System.out.println("#list subdir");
        Files.list(Paths.get("."))
                .filter(Files::isDirectory)
                .forEach(System.out::println);
    }
}
/*
output:
#list all files
./.idea
./05-list-files-in-dir.iml
./pom.xml
./src
./target
#list subdir
./.idea
./src
./target
 */
