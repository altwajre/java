package com.company.app;

import lombok.Cleanup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class App {
    public static void main(String... args) throws IOException {
        @Cleanup InputStream fis = new FileInputStream("lombok.txt");
        int content;
        while ((content = fis.read()) != -1){
            System.out.print((char)content);
        }
    }
}
/*
https://projectlombok.org/features/Cleanup.html
Use @Cleanup to ensure a given resource automatically cleaned up before the code execution path exits your current scope.

output:
abc
xyz
 */
