# Groovy knowledge for Spock testing

## Spock

- Groovy shows all intermediate operations when test failed

## What you need to know about Groovy

In a nutshell, Groovy
- Is a dynamic language (Java is static)
- Is a strongly typed language (same as Java)
- Is object-oriented (same as Java)
- Comes with the GDK (Java has the JDK)
- Runs on the JVM (same as Java)
- Favors concise code (Java is considered verbose compared to Groovy)
- Offers its own libraries (for example, web and object relational frameworks)
- Can use any existing Java library as-is (Java can also call Groovy code)
- Has closures (Java 8 has lambda expressions)
- Supports duck typing[1] (Java has strict inheritance)

## Groovy vs Java

Groovy
- Classes are public by default.
- Fields are private by default.
- Getters and setters are automatically created during runtime and thus don't need to be included in the class declarations
- Semicolons are optional and should only be used in case of multiple statements in the same line.
- String interpolation: "$name" evaluate expression in double quote
- `return` keyword is optional, the last evaluated statement in a method is the result.

Groovy script
- main method is optional

## Accessing Java classes in a Groovy script

Java and Groovy integration
- Groovy code can create Java classes with the new keyword.
- Groovy code can call Java methods.
- Groovy classes can extend Java classes.
- Groovy class can implement Java interfaces.

## Declaring variables and methods Groovy

**dynamically typed def keyword**

*optional typing in variables*
def name = "Tom"

*optional typing in methods*
def createName(String firstName, String lastName){
  return "$lastName, $firstName"
}

## What == means in Groovy
- == calls the equals() of the object
- Identity is handled by the `is` keyword, obj1.is(obj2)

## Using maps and lists in Groovy

Java
```
Map<String, Integer> wordCounts = new HashMap<>();
        wordCounts.put("Hello", 1);
        wordCounts.put("Java", 1);
        wordCounts.put("World", 2);
```

Groovy
```
Map<String, Integer> wordCounts2 = ["Hello": 1, "Groovy": 1, "World": 2]
```
