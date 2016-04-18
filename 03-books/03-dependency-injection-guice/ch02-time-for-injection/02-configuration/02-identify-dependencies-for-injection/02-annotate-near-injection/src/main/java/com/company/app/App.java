package com.company.app;

import com.google.inject.*;
import com.google.inject.Guice;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

interface SpellChecker{
    public boolean check(String text);
}
// French implementation of SpellChecker
class FrenchSpellChecker implements SpellChecker{
    public boolean check(String text) {
        System.out.println("#FrenchSpellChecker.check()");
        return false;
    }
}
// English implementation of SpellChecker
class EnglishSpellChecker implements SpellChecker{
    public boolean check(String text) {
        System.out.println("#EnglishSpellChecker.check()");
        return false;
    }
}
class Emailer{
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
@interface English {}
@BindingAnnotation @Target({ FIELD, PARAMETER, METHOD }) @Retention(RUNTIME)
@interface French {}
//# binding services to combinatorial keys - annotatedWith(English.class)
class SpellingModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(SpellChecker.class).annotatedWith(English.class).to(EnglishSpellChecker.class);
        bind(SpellChecker.class).annotatedWith(French.class).to(FrenchSpellChecker.class);
    }
}
class SpellCheckerClient{
    private SpellChecker spellChecker;
    @Inject  //# annotate near injection below
    public SpellCheckerClient(@French SpellChecker spellChecker){
        this.spellChecker = spellChecker;
    }
    public boolean check(String text){
        spellChecker.check("hi");
        return false;
    }
}
public class App
{
    public static void main( String[] args )
    {
        Guice.createInjector(new SpellingModule())
                .getInstance(SpellCheckerClient.class).check("hi");
    }
}
/*
https://github.com/google/guice/wiki/BindingAnnotations

output:
#FrenchSpellChecker.check()
 */