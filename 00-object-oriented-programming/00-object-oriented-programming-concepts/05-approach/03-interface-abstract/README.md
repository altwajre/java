# Interfaces and Abstract Classes

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video5_3

## Interface

- An interface is a class that is completely abstract (all methods are abstract), it defines methods a concrete subclass must implement (an API)
- Interface methods are implicitly public and abstract
- In an Interface variables must be public, static, and final (interfaces have only constants)
- An interface must be declared with the keyword interface
- A Class implements an Interface by using the implements keyword

> Rules

- An interface can only extend another interface (use extends on another interface)
- An interface cannot implement another interface (cannot use implements on another interface)
- Interface types can be used polymorphically
