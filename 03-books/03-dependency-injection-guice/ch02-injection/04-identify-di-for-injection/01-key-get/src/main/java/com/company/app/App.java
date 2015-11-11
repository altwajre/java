package com.company.app;

import com.google.inject.*;
import com.google.inject.Guice;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

public class App
{
    interface SpellChecker{
        public boolean check(String text);
    }
    // French implementation of SpellChecker
    static class FrenchSpellChecker implements SpellChecker{
        public boolean check(String text) {
            System.out.println("#FrenchSpellChecker.check()");
            return false;
        }
    }
    // English implementation of SpellChecker
    static class EnglishSpellChecker implements SpellChecker{
        public boolean check(String text) {
            System.out.println("#EnglishSpellChecker.check()");
            return false;
        }
    }
    static class Emailer{
        private SpellChecker spellChecker;
        // Injected spellchecker wired via constructor
        public Emailer(SpellChecker spellChecker){
            this.spellChecker = spellChecker;
        }
        public void send(String text){
            // provided dependency is used transparently
            spellChecker.check(text);
        }
    }
    @BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
    public @interface English {}
    @BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
    public @interface French {}
    //# binding services to combinatorial keys - annotatedWith(English.class)
    static class SpellingModule extends AbstractModule{
        @Override
        protected void configure() {
            bind(SpellChecker.class).annotatedWith(English.class).to(EnglishSpellChecker.class);
            bind(SpellChecker.class).annotatedWith(French.class).to(FrenchSpellChecker.class);
        }
    }
    //# output: #EnglishSpellChecker.check()
    public static void main( String[] args )
    {
        //# using Key.get()
        Guice.createInjector(new SpellingModule())
                .getInstance(Key.get(SpellChecker.class, English.class))
                .check("Hello!");
    }
}
