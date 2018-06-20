# Aspect-Oriented Programming and Components

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/adf9f2b9-d4bc-4671-bd4d-259436eda0ba.xhtml

Aspect-oriented programming (AOP) addresses a common functionality, that spans across an application, 
but cannot be otherwise abstracted in a single module using traditional object-oriented techniques. 
This repeated functionality is often referred to as cross-cutting concerns. A common example is logging-normally, 
loggers are created within classes and then their methods are called inside the methods of the classes. 
This helps with the debugging and tracing of events in an application, but it is not really related to the actual functionality in any way.

AOP recommends that cross-cutting concerns are abstracted and encapsulated in their own modules. 
