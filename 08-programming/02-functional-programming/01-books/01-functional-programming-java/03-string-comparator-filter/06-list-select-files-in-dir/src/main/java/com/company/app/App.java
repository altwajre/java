package com.company.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class App
{
    public static void main( String[] args ) throws IOException {
        System.out.println("#anonymousClass");
        anonymousClass();
        System.out.println("#lambdaExpression");
        lambdaExpression();
        System.out.println("#listHiddenFilesLambda");
        listHiddenFilesLambda();
        System.out.println("#listHiddenFilesMethodReference");
        listHiddenFilesMethodReference();
    }

    private static void listHiddenFilesMethodReference() {
        Arrays.asList(new File(".").listFiles(File::isHidden)).forEach(System.out::println);
    }

    private static void listHiddenFilesLambda() {
        final File[] files = new File(".").listFiles(file -> file.isHidden());
        Arrays.asList(files).forEach(System.out::println);
    }

    private static void lambdaExpression() throws IOException {
        Files.newDirectoryStream(Paths.get("src"), path -> path.toString().endsWith(".java"))
                .forEach(System.out::println);
    }

    private static void anonymousClass() {
        final String[] files = new File("src").list(new java.io.FilenameFilter(){
            public boolean accept(final File dir, final String name){
                return name.endsWith(".java");
            }
        });
        Arrays.asList(files).forEach(System.out::println);
    }
}
/*
output:
#anonymousClass
bar.java
foo.java
#lambdaExpression
src/bar.java
src/foo.java
#listHiddenFilesLambda
./.DS_Store
./.idea
#listHiddenFilesMethodReference
./.DS_Store
./.idea
 */
