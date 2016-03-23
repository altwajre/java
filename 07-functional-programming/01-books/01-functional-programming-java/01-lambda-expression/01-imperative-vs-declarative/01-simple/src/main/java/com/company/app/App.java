package com.company.app;

import java.util.Arrays;
import java.util.List;

public class App
{
    static void findTomImperative(List<String> names){
        boolean found = false;
        for(String name : names){
            if(name.equals("Tom")){
                found = true;
                break;
            }
        }
        System.out.println("Found Tom?: " + found);
    }

    // concise and easier to read
    static void findTomDeclarative(List<String> names){
        System.out.println("Found Tom?: " + names.contains("Tom"));
    }

    public static void main( String[] args )
    {
        List<String> names = Arrays.asList("Will", "Tom", "Dick", "Harry");
        System.out.println("findTomImperative");
        findTomImperative(names);
        System.out.println("findTomDeclarative");
        findTomDeclarative(names);
    }
}
/*
output:
findTomImperative
Found Tom?: true
findTomDeclarative
Found Tom?: true
 */