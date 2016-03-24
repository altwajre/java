package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App
{
    final static List<String> friends = Arrays.asList("Tom", "Dick", "Harry", "Nate", "Bill", "Will", "Neal");
    static void pickNameImperative(final List<String> names, final String startingLetter){
        String foundName = null;
        for(String name : names){
            if(name.startsWith(startingLetter)){
                foundName = name;
                break;
            }
        }
        System.out.printf("A name starting with %s: ", startingLetter);

        if(foundName != null){
            System.out.println(foundName);
        }
        else{
            System.out.println("No name found");
        }
    }
    static void pickNameFunctional(final List<String> names, final String startingLetter){
        final Optional<String> foundName = names.stream()
                .filter(s -> s.startsWith(startingLetter))
                .findFirst();
        System.out.printf("A name starting with %s: %s", startingLetter, foundName.orElse("No name found"));
        System.out.println("");
    }
    public static void main( String[] args )
    {
        System.out.println("#pickNameImperative");
        pickNameImperative(friends, "N");
        pickNameImperative(friends, "Z");

        System.out.println("#pickNameFunctional");
        pickNameFunctional(friends, "N");
        pickNameFunctional(friends, "Z");

        final Optional<String> foundName = friends.stream()
                .filter(s -> s.startsWith("N"))
                .findFirst();
        foundName.ifPresent(name -> System.out.println("Hello " + name));
    }
}
/*
output:
#pickNameImperative
A name starting with N: Nate
A name starting with Z: No name found
#pickNameFunctional
A name starting with N: Nate
A name starting with Z: No name found
Hello Nate
 */