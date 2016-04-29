package com.company.app;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Set;

@Value
@Builder
class BuilderExample{
    private String name;
    private int age;
    @Singular
    private Set<String> occupations;
}
public class App {
    public static void main(String... args){
        BuilderExample builderExample = BuilderExample.builder().name("Tom").age(10).build();
        System.out.println(builderExample);
    }
}
/*
https://projectlombok.org/features/Builder.html

output:
BuilderExample(name=Tom, age=10, occupations=[])
 */