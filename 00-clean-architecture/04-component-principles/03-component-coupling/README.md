# Component Coupling

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch14.xhtml

## The Acyclic Dependencies Principle

Allow no cycles in the Component dependency graph.

> The Weekly Build

Developers work on private copies of code for the first four days of the week.
Then, on Friday, they integrate all their changes and build the system.

> Eliminating Dependency Cycles

Partition the development environment into releasable components.
The components become unit of work that can be Responsibility of a single developer, or a team of developers.
The components dependency structure should be a directed graph, has no cycles.

> The Effect of A Cycle In The Component Dependency Graph

They will be stepping all over one another because they must all use exactly the same release of one another's components.
Cycles make it difficult to isolate components, so unit testing and releasing become difficult and error prone.
Cycles make it difficult to work out the order in which you must build the components. Indeed, there is no correct order.

> Breaking the Cycle

1. Dependency Inversion Principle (DIP).
Create an interface inverts the dependency between the components.

2. Create a new component that both Components depend on. Move the classes that they both depend on into the new component.

> The "Jitters"

When cycles occur, they must be broken somehow.
Sometimes this will mean creating new components, making the dependency structure grow.

## Top-Down Design

The component structure cannot be designed from the top down.
It is not one of the first things about the system that is design, but rather evolves as the system grows and changes.
Component dependency diagrams are a map to the buildability and maintainability of the application, so it is why they aren't designed at the beginning of the project.

As the application continues to grow, we start to become concerned about creating reusable elements.
At this point, the Common Reuse Principle (CRP) begins to influence the composition of the components.
Finally, as cycles appear, the Acyclic Dependencies Principle (ADP) is applied and the component dependency graph jitters and grows.

If design the component dependency structure before we designed any classes, it would likely fail.
We would not know much about common closure (group for maintenance), we would be unaware of any reusable elements, and we would almost certainly create components that produced dependency cycles.
Thus the component dependency structure grows and evolves with the logical design of the system.

## The Stable Dependencies Principle

Depend in the direction of stability.

Ensure that modules are intended to be easy to change are not depended on by modules that are harder to change.
