package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

class SpellChecker{
    public void check(String text){}
}
class Emailer{
    private SpellChecker spellChecker;
    @Inject
    //# Setter injection
    // constructor executed, setter injection executed, and then provide an object
    public void setSpellChecker(SpellChecker spellChecker){
        this.spellChecker = spellChecker;
    }
    public void send(String text){
        System.out.println("#Emailer.send()");
        spellChecker.check(text);
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Emailer.class).send("hello");
    }
}
/*
output:
#Emailer.send()
 */