# Aspect-Oriented Programming and Components

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/adf9f2b9-d4bc-4671-bd4d-259436eda0ba.xhtml

Aspect-oriented programming (AOP) addresses a common functionality, that spans across an application, 
but cannot be otherwise abstracted in a single module using traditional object-oriented techniques. 
This repeated functionality is often referred to as cross-cutting concerns. A common example is logging-normally, 
loggers are created within classes and then their methods are called inside the methods of the classes. 
This helps with the debugging and tracing of events in an application, but it is not really related to the actual functionality in any way.

AOP recommends that cross-cutting concerns are abstracted and encapsulated in their own modules. 

## Components in Scala

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/336c5ce5-18c4-4bfd-aaa5-17d00dabe162.xhtml

Components are parts of an application that are meant to be combined with other parts of the application. 
They should be reusable in order to achieve less code duplication. Components typically have interfaces, 
which describe the services they provide and a number of services or other components they depend on.

In large applications, we usually see multiple components that are integrated to work together. 
Describing the services that a component provides is usually straightforward, and it is done with the help of interfaces. 
Integrating other components, however, could sometimes require a developer to do extra work. 
This is usually done by passing the interface of the requirement as a parameter. 
However, imagine a large application in which we might have a lot of requirements; wiring things up could take time and effort. 
Moreover, every time a new requirement comes up, we would have to do quite a lot of refactoring.
