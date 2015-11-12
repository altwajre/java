package com.company.app;

import com.google.inject.Guice;
import com.google.inject.Inject;

public class App
{
    static class SpellChecker{
        public void check(String text){}
    }
    static class Emailer{
        private SpellChecker spellChecker;
        @Inject
        // constructor injection executed, and then provide an object
        public Emailer(SpellChecker spellChecker){
            this.spellChecker = spellChecker;
        }
        public void send(String text){
            System.out.println("#Emailer.send()");
            spellChecker.check(text);
        }
    }
    // output: #Emailer.send()
    public static void main( String[] args )
    {
        Guice.createInjector().getInstance(Emailer.class).send("hello");
    }
}
