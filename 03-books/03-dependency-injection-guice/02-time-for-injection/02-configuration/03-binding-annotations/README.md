# binding annotations

https://github.com/google/guice/wiki/BindingAnnotations

We’ve already seen that a service generally has some kind of identify, whether an arbitrary string identifier, the
class it belongs to, or some other combinatorial key.

## Type checked annotation

http://blog.muhuk.com/2015/05/28/using_guice_effectively.html#.VxcZ4eYrJTY

@English - public EnglishEditor(@English SpellChecker spellChecker) {

```
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
Injector injector = Guice.createInjector(new SpellingModule());
EnglishEditor englishEditor = injector.getInstance(EnglishEditor.class);
englishEditor.spellCheck("englishEditor");
```

## Identify components and dependencies
A dependency is some implementation of a service. It may only be a flat object with no dependencies of its own. Or it
may be an object with a vast graph of interconnected dependencies, which themselves have dependencies, and so on.

A dependency is any particular permutation of objects in a graph that represents the original service; that is, all
permutations obey the same contract.
Permutations of the same service may be thought of as variants (or implementations) of that service.

### Keys provide a binding between a label and the object graph that implements the service it identifies.

If you wanted to use one particular implementation instead of another, you’d only need to point the dependent to its key.
Keys are also essential when we want to leverage other features of dependency injectors:
 - Interception - Modifying an object’s behavior
 - Scope - managing an object’s state
 - Lifecycle - notifying an object of significant events

### Combinatorial keys: a comprehensive solution
Earlier, I described a sample combinatorial key for an English variant of our favorite email service.
The ordered pair [Emailer.class, “english”] is one case of a combinatorial key consisting of a type key and a string key.
The type key identifies the service that dependents rely on, in a safe and consistent manner.

An emailer that depends on a SpellChecker:
[Emailer.class, “withSpellChecker”]

Consider a more complex take on this example, where our emailer depends on a SpellChecker, which itself depends on a
dictionary to resolve word corrections.
[Emailer.class, “withSpellCheckerAndDictionary”]

Each of these keys will result in an erroneous injector configuration that won’t be detected until runtime. While there
are certainly far fewer possible total errors with this approach, there are nonetheless the same perils and pitfalls so
long as we rely on a string key in any consistent and foundational manner.

Consider the following combinatorial keys that are composed of a type and an annotation type:
Misspelling the annotation name results in a compiler error, which is an early and clear indication of the problem.
This is exactly what were after. What we have done is effectively replace the string key with a second type key.
In combination with the service’s type key, this retains the qualities of good behavior in well-chosen keys.

We use the @English annotation to distinguish variant implementations of not only the Eamiler but also the Dictionary
and TextEditor services. These keys are self documenting and still give us the flexibility of a string key. Since
annotations are cheap to create (hardly a couple lines of code) and reuse, this approach scales nicely too.

