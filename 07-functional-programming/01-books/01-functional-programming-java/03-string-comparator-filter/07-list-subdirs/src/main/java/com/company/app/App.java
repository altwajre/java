package com.company.app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("#imperativeForLoop");
        imperativeForLoop();

        System.out.println("#functionalFlatMap");
        functionalFlatMap();
    }

    private static void functionalFlatMap() {
        List<File> files = Stream.of(new File(".").listFiles())
                .flatMap(f -> f.listFiles() == null ? Stream.of(f) : Stream.of(f.listFiles()))
                .collect(Collectors.toList());
        files.forEach(System.out::println);
    }

    private static void imperativeForLoop() {
        List<File> files = new ArrayList<>();
        File[] filesInCurrentDir = new File(".").listFiles();
        for(File file : filesInCurrentDir){
            File[] filesInSubDir = file.listFiles();
            if(filesInSubDir != null){
                files.addAll(Arrays.asList(filesInSubDir));
            }
            else{
                files.add(file);
            }
        }
        files.forEach(System.out::println);
    }
}
/*
output:
#imperativeForLoop
./.idea/compiler.xml
./.idea/copyright
./.idea/encodings.xml
./.idea/libraries
./.idea/misc.xml
./.idea/modules.xml
./.idea/workspace.xml
./07-list-subdirs.iml
./pom.xml
./src/main
./src/test
./target/classes
./target/generated-sources
./target/generated-test-sources
./target/test-classes
#functionalFlatMap
./.idea/compiler.xml
./.idea/copyright
./.idea/encodings.xml
./.idea/libraries
./.idea/misc.xml
./.idea/modules.xml
./.idea/workspace.xml
./07-list-subdirs.iml
./pom.xml
./src/main
./src/test
./target/classes
./target/generated-sources
./target/generated-test-sources
./target/test-classes

 */
