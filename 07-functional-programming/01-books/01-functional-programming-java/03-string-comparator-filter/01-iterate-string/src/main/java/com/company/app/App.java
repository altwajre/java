package com.company.app;

public class App {
    static void printChar(int aChar) {
        System.out.println((char) aChar);
    }

    public static void main(String[] args) {
        final String str = "w00t";
        // instance method reference to print numbers right away
        str.chars()
                .forEach(System.out::println);

        // static method reference to print characters
        str.chars()
                .forEach(App::printChar);

        // use mapToObj() to convert ints to characters
        str.chars()
                .mapToObj(c -> Character.valueOf((char)c))
                .forEach(System.out::println);

        // static method filter out only digits from the string
        str.chars()
                .filter(Character::isDigit)
                .forEach(App::printChar);
    }
}
/*
output:
119
48
48
116
w
0
0
t
w
0
0
t
0
0
 */
