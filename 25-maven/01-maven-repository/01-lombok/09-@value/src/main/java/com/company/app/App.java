package com.company.app;

import lombok.AccessLevel;
import lombok.Value;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

@Value
class ValueExample{
    String name;
    @Wither(AccessLevel.PACKAGE) @NonFinal
    double score;
    protected String[] tags;
}
public class App {
    public static void main(String... args){
        ValueExample valueExample = new ValueExample("Tom", 8, null);
        System.out.println(valueExample.getName());
        System.out.println(valueExample.getScore());
        System.out.println(valueExample);
    }
}
/*
https://projectlombok.org/features/Value.html

@Value is the immutable variant of @Data; all fields are made private and final by default, and setters are not
generated. The class itself is also made final by default, because immutability is not something that can be forced
onto a subclass.

output:
Tom
8.0
ValueExample(name=Tom, score=8.0, tags=null)
 */
