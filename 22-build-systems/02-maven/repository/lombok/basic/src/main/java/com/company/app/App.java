package com.company.app;

import lombok.val;

import java.util.ArrayList;

/**
 * intellij-plugin: https://github.com/mplushnikov/lombok-intellij-plugin
 * maven: https://projectlombok.org/mavenrepo/
 * https://projectlombok.org/features/val.html
 */
public class App
{
    public String example(){
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }
    public static void main( String[] args )
    {
        System.out.println(new App().example());
    }
}
/*
output:
hello, world!
 */