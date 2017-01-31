# Favor composition over inheritance

```
Inheritance is a powerful way to achieve code reuse, but it is not always the best tool for the job. Use inappropriately,
it leads to fragile software. It is safe to use inheritance within a package, where the subclass and superclass
implementations are under the control of the same programmers. Inheriting from ordinary concrete classes across package
boundaries is dangerous.
Unlike method invocation, inheritance violates encapsulation. In other words, a subclass depends on the implementation
details of its superclass for its proper function. The superclass's implementation may change from release to release, 
the subclass may break.
```