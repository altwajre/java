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

The @Builder annotation produces complex builder APIs for your class.
@Builder lets you automatically produce the code required to have your class be instantiable with code such as:
Person.builder().name(“Tom”).age(8).build();

The @Singular annotation treats that

output:
BuilderExample(name=Tom, age=10, occupations=[])
 */