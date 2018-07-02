# Mediator

> What it is not so good for

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/a279e09e-0b98-4699-aaa4-c7d56c6a7a32.xhtml

A possible pitfall when using the mediator design pattern is to put a lot of different interaction functionalities in one class.
Mediators tend to become more complex with time, and it will become hard to change or understand what our application can do at all. 
If we actually have many more classes that have to interact with each other, it will imminently affect the mediator as well.
