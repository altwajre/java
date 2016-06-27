package com.company.app;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Developer {
    private String name;
    private Set<String> languages;

    public Developer(String name) {
        this.languages = new HashSet<>();
        this.name = name;
    }
    public void add(String language) {
        this.languages.add(language);
    }
    public Set<String> getLanguages() {
        return languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class App {
    public static void main(String[] args) {
        List<Developer> team = new ArrayList<>();
        Developer tom = new Developer("Tom");
        tom.add("C");
        tom.add("C++");
        tom.add("C#");
        
        Developer dick = new Developer("Dick");
        dick.add("Java");
        dick.add("JavaScript");
        
        team.add(tom);
        team.add(dick);

        Stream<Developer> developerStream = team.stream();
        Stream<Set<String>> languageSetStream = developerStream.map(d -> d.getLanguages());
        Stream<String> languageStream = languageSetStream.flatMap(l -> {
            System.out.println("flapMap");
            System.out.println(l);
            return l.stream();
        });
        List<String> languages = languageStream.collect(Collectors.toList());
        System.out.println(languages);

        String[] words = {"Hello", "World"};

        Stream<String[]> splitedWordsStream = Arrays.stream(words).map(w -> w.split(""));
        splitedWordsStream.forEach(s -> {
            System.out.println(Arrays.toString(s));
        });

        Stream<String> stringStream = Arrays.stream(words)
                .map(word -> word.split(""))
                .flatMap(Arrays::stream); // map each array with the contents of the stream.
        List<String> result = stringStream
                .distinct()
                .collect(Collectors.toList());
        System.out.println(result);
    }

}
/*
output:
flapMap
[C#, C++, C]
flapMap
[Java, JavaScript]
[C#, C++, C, Java, JavaScript]
[H, e, l, l, o]
[W, o, r, l, d]
[H, e, l, o, W, r, d]
 */
