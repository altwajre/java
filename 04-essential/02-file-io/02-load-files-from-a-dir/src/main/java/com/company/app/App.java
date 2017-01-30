package com.company.app;

import java.io.File;
import java.util.Arrays;

public class App
{
    public static void main( String[] args )
    {
        String workingDir = System.getProperty("user.dir");
        System.out.println(workingDir);
        File folder = new File(workingDir + "/src/main/resources/api");
        File[] listOfFiles = folder.listFiles();

//        for(int i = 0; i < listOfFiles.length; i++){
//            if(listOfFiles[i].isFile()){
//                String name = listOfFiles[i].getName().toString();
//                System.out.println(name);
//            }
//        }

        Arrays.stream(listOfFiles).forEach(f -> {
            if(f.isFile()){
                System.out.println(f.getName());
            }
        });

    }
}
/*
output:
api_bar.txt
api_foo.txt
 */
