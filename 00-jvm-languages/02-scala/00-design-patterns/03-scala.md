# Scala

Scala attempts to unify object-oriented and functional programming.

## Traits and Mixin Composition

> Traits

- Traits or mixins are essentially abstract classes without constructors. Traits can be used anywhere an abstract class is expected, but only traits can be mixed in a class via mixin composition. They can be though of as abstract subclass.

- Traits can be used as interfaces in Java, but can also contain information code.

> Mixin

- Mixin composition is an extension mechanism comparable to standard class extension mechanisms, such as single inheritance found in Java, although more general. The result of a mixin composition is a new class.

## Components in Scala

A component is a program part that is to be combined with other parts in an application. Components should be definition be reusable. A component typically has an interface describing the services it provide, another aspect is the services that is required by the component itself.

- Component = class
- Runtime component = object
- Interface = abstract class (or trait)
- Required component = abstract member or explicit self type
- Composition = mixin composition
