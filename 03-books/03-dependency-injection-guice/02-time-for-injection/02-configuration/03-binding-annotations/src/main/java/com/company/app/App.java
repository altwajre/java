package com.company.app;

import com.google.inject.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

interface SpellChecker{
    public boolean check(String text);
}
class FrenchSpellChecker implements SpellChecker{
    @Override
    public boolean check(String text) {
        System.out.println("FrenchSpellChecker.check(): " + text);
        return false;
    }
}
class EnglishSpellChecker implements SpellChecker{
    @Override
    public boolean check(String text) {
        System.out.println("EnglishSpellChecker.check(): " + text);
        return false;
    }
}
class Emailer{
    final SpellChecker spellChecker;
    public Emailer(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void send(String text){
        spellChecker.check(text);
    }
}
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@interface English{}
@BindingAnnotation
@Retention(RetentionPolicy.RUNTIME)
@interface French{}
class SpellingModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(SpellChecker.class).annotatedWith(English.class).to(EnglishSpellChecker.class);
        bind(SpellChecker.class).annotatedWith(French.class).to(FrenchSpellChecker.class);
    }
}
// inline annotation - inject EnglishSpellChecker
class EnglishEditor{
    final SpellChecker spellChecker;
    @Inject // using inline annotation to inject EnglishSpellChecker
    public EnglishEditor(@English SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }
    public void spellCheck(String text){
        spellChecker.check(text);
    }
}
public class App {
    private static void constructByHand() {
        System.out.println("constructByHand");
        Emailer englishEmailer = new Emailer(new EnglishSpellChecker());
        englishEmailer.send("englishEmailer constructByHand");
        Emailer frenchEmailer = new Emailer(new FrenchSpellChecker());
        frenchEmailer.send("frenchEmailer constructByHand");
    }
    private static void dependencyInjection() {
        System.out.println("dependencyInjection");

        Injector injector = Guice.createInjector(new SpellingModule());
        SpellChecker englishSpellChecker = injector
                .getInstance(Key.get(SpellChecker.class, English.class));
        Emailer englishEmailer = new Emailer(englishSpellChecker);
        englishEmailer.send("englishEmailer dependencyInjection");

        SpellChecker frenchSpellChecker = injector
                .getInstance(Key.get(SpellChecker.class, French.class));
        Emailer frenchEmailer = new Emailer(frenchSpellChecker);
        frenchEmailer.send("frenchEmailer dependencyInjection");
    }
    public static void main(String... args){
        usingKeys();
        usingInlineAnnotation();
    }

    private static void usingInlineAnnotation() {
        System.out.println("\n#usingInlineAnnotation");
        Injector injector = Guice.createInjector(new SpellingModule());
        EnglishEditor englishEditor = injector.getInstance(EnglishEditor.class);
        englishEditor.spellCheck("englishEditor");
    }

    private static void usingKeys() {
        System.out.println("#usingKeys");
        constructByHand();
        dependencyInjection();
    }
}
/*
https://github.com/google/guice/wiki/BindingAnnotations

output:
#usingKeys
constructByHand
EnglishSpellChecker.check(): englishEmailer constructByHand
FrenchSpellChecker.check(): frenchEmailer constructByHand
dependencyInjection
EnglishSpellChecker.check(): englishEmailer dependencyInjection
FrenchSpellChecker.check(): frenchEmailer dependencyInjection

#usingInlineAnnotation
EnglishSpellChecker.check(): englishEditor
 */