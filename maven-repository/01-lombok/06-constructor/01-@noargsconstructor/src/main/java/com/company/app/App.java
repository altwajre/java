package com.company.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter
@NoArgsConstructor
class NoArgsExample{
    @NonNull
    private String field;
}
public class App {
    public static void main(String... args){
        NoArgsExample noArgsExample = new NoArgsExample();
        System.out.println(noArgsExample.getField());
    }
}
/*
@NoArgsConstructor will generate a constructor with no parameters. If this is not possible (because of final fields),
a compiler error will result instead, unless @NoArgsConstructor(force = true) is used, then all final fields are
initialized with 0 / false / null. For fields with constraints, such as @NonNull fields, no check or assignment is
generated, so be aware that these constraints may then not be fulfilled until those fields are properly initialized later.

output:
null
 */