package com.company.app;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
class AllArgsExample{
    private int x, y;
    @NonNull
    private String description;
}
public class App {
    public static void main(String... args){
        AllArgsExample allArgsExample = new AllArgsExample(1, 2, "desc");
        System.out.println("x: " + allArgsExample.getX());
        System.out.println("y: " + allArgsExample.getY());
        System.out.println("description: " + allArgsExample.getDescription());
    }
}
/*
@AllArgsConstructor generates a constructor with 1 parameter for each field in your class. Fields marked with @NonNull
result in null checks on those parameters.

output:
x: 1
y: 2
description: desc
 */