package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

class SpellChecker{
    public void check(String text) { }
}
class Emailer{
    private SpellChecker spellChecker; // Dependencies wired via constructor
    @Inject // An annotation tells Guice to use this constructor
    public Emailer(SpellChecker spellChecker){
        this.spellChecker = spellChecker;
    }
    public void send(String text){
        System.out.println("#Emailer.send()");
        spellChecker.check(text);
        // send if ok...;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Emailer.class).send("Hello");
    }
}
/*
output:
#Emailer.send()
 */