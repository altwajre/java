# Dependency Injection: Design patterns using Spring and Guice

https://www.safaribooksonline.com/library/view/dependency-injection-design/9781933988559/

## dependency and service
```
A dependency is some implementation of a service.
It may only be a flat object with no dependencies of its own.
Or it may be an object with large graph of interconnected dependencies, which themselves have dependencise, and so on.
```

## Separating infrastructure and application logic

### Infrastructure logic - dependency injectors

Focused components with code only to deal with their primary business purpose

- Rendering a web page
- Sending email
- Purchasing stock

The infrastructure logic is essential to application, it is important to distinguish it from the application logic.

### Application logic

Logic for constructing and assembling object graphs, obtaining connections to databases, setting up network sockets, or
crunching text through spellcheckers is all peripheral to the core purpose of the application.

Injectors assemble clients and services as per configuration, into comprehensive applications.

## injection

### Construct objects with di

```
Guice.createInjector().getInstance(Emailer.class).send("Hello");
```

### Configuration

https://github.com/google/guice/wiki/BindingAnnotations

bind-to

```
class SortModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(SortStrategy.class).to(QuickSort.class); // Service binding
    }
}
public class app {
    public static void main(String[] args){
        SortStrategy sortStrategy = Guice.createInjector(new SortModule()).getInstance(SortStrategy.class);
        sortStrategy.sort(null);
    }
}
```

combinatorial-bindings

```
//# binding services to combinatorial keys - annotatedWith(English.class)
class SpellingModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(SpellChecker.class).annotatedWith(English.class).to(EnglishSpellChecker.class);
        bind(SpellChecker.class).annotatedWith(French.class).to(FrenchSpellChecker.class);
    }
}
//# LOOK HERE
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
```

## Identifying dependencies for injection

### namespaces

Namespaces are a more elegant and natural for your key space and more readable.

"Set.BinaryTree" and "Set.HashTable" are better than "binaryTreeSet" and "hashTableSet"
