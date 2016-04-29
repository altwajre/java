package com.company.app;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
class RequiredArgsExample{
    private int x, y;
    @NonNull
    private String description;
}
public class App {
    public static void main(String... args){
        RequiredArgsExample requiredArgsExample = new RequiredArgsExample("desc");
        System.out.println("description: "+requiredArgsExample.getDescription());
        System.out.println("x: " + requiredArgsExample.getX());
    }
}
/*
@RequiredArgsConstructor generates a constructor with 1 parameter for each field that requires special handling.
All non-initialized final fields get a parameter, as well as any fields that are marked as @NonNull that aren't
initialized where they are declared. For those fields marked with @NonNull, an explicit null check is also generated.

output:
description: desc
x: 0
 */