# Applying GoF Design Patterns

https://learning.oreilly.com/library/view/applying-uml-and/0131489062/ch26.html

The shift of focus (to patterns) will have a profound and enduring effect on the way we write programs.

## Objectives

- Introduce and apply some GoF design patterns
- Show GRASP principles as a generalization of other design patterns

This chapter explores OO design for use-case realizations for the NextGen case study, providing support for external third-party services whose interfaces may vary, more complex product pricing rules, and pluggable business rules.

# Adapter (GOF)

The Polymorphism pattern and its solution is more specifically an example of the GoF Adapter pattern.

> Problem

How to resolve incompatible interfaces, or provide a stable interface to similar components with different interfaces?

> Solution:

Convert the original interface of a component into another interface, through an intermediate adapter object.

> Related Patterns

A resource adapter that hides an external system may also be considered a `Facade` object, as it wraps access to the subsystem or system with a single object `(Facade)`. However, the motivation to call it a resource adapter especially exists when the wrapping object provides `adaptation to varying external interfaces`.

# Factory

> Advantages

- Separate the responsibility of complex creation into cohesive helper objects.
- Hide potentially complex creation logic.
- Allow introduction of performance-enhancing memory management strategies, such as object caching or recycling.

> Problem

Who should be responsible for creating objects when there are special considerations, such as complex creation logic, a desire to separate the creation responsibilities for better cohesion, and so forth?

> Solution

Create a Pure Fabrication object called a Factory that handles the creation.
