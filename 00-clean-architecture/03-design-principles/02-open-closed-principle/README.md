# Open Closed Principle

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch8.xhtml

A software artifact should be open for extension but closed for modification.

## OCP in Architectural level

Architects separate functionality based on how, why and when it changes, and then organize that separated functionality into a hierarchy of components.
Higher-level components in that hierarchy are protected from the changes made to lower-level components.

## Note

Classes marked with <I> are interfaces; those marked with <DS> are data structures. Open arrowheads are using relationships. Closed arrowheads are implements or inheritance relationships.

An arrow pointing from class A to class B means that the source code of class A mentions the name of class B, but class B mentions nothing about class A.
