# Open Closed Principle

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch8.xhtml

A software artifact should be open for extension but closed for modification.

The OCP is driving forces behind the architecture os systems.
The goal is to make the system easy to extend without incurring a high impact of change.
This goal is accomplished by partitioning the system into components, and arranging those components into a dependency hierarchy that protects higher-level components from changes in lower-level components.

## OCP in Architectural level

Architects separate functionality based on how, why and when it changes, and then organize that separated functionality into a hierarchy of components.
Higher-level components in that hierarchy are protected from the changes made to lower-level components.

## Note

Classes marked with <I> are interfaces; those marked with <DS> are data structures. Open arrowheads are using relationships. Closed arrowheads are implements or inheritance relationships.

An arrow pointing from class A to class B means that the source code of class A mentions the name of class B, but class B mentions nothing about class A.

http://www.oodesign.com/open-close-principle.html

> Intent

- Software entities like classes, modules and functions should be open for extension but closed for modifications.

https://en.wikipedia.org/wiki/Open/closed_principle

> Example

http://joelabrahamsson.com/a-simple-example-of-the-openclosed-principle/
