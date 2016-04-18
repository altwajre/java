# Dependency Injection: Design patterns using Spring and Guice

https://www.safaribooksonline.com/library/view/dependency-injection-design/9781933988559/

## dependency and service
```
A dependency is some implementation of a service.
It may only be a flat object with no dependencies of its own.
Or it may be an object with large graph of interconnected dependencies, which themselves have dependencise, and so on.
```

## Identifying dependencies for injection

### namespaces

Namespaces are a more elegant and natural for your key space and more readable.

"Set.BinaryTree" and "Set.HashTable" are better than "binaryTreeSet" and "hashTableSet"

## ch02 injection

```
Construct objects with di
Guice.createInjector().getInstance(Emailer.class).send("Hello");


```
