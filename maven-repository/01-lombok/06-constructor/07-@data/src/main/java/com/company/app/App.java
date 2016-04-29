package com.company.app;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
class DataExample{
    private final String name;
    @Setter(AccessLevel.PACKAGE)
    private int age;
    @NonNull
    private double score;
    private String[] tags;
}
public class App {
    public static void main(String... args){
        DataExample dataExample = new DataExample("Tom", 8);
        System.out.println("name: " + dataExample.getName());
        System.out.println("score: " + dataExample.getScore());
    }
}
/*

@Data is like having implicit @Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor annotations
on the class (except that no constructor will be generated if any explicitly written constructor exists).

output:
name: Tom
score: 8.0
 */
