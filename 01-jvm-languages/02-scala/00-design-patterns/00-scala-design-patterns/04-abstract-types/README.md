# Abstract and Self Types

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/31807099-24ea-49c2-8dc1-8dcc978987d7.xhtml

- Abstract types
- Polymorphism
- Self types

## Abstract types

> Use generics

- If you need just type instantiation; a good example is the standard collection classes
- If you are creating a family of types

> Use abstract types

- If you want to allow people to mix in types using traits
- If you need better readability in scenarios where both could be interchangeable
- If you want to hide the type definition from the client code

## Self types

One of the features of good code is the separation of concerns. Developers should aim to make classes and their methods responsible for one and only one thing. This helps in testing, maintaining, and simply understanding code better. Remember - simple is always better.

However, it is inevitable that when writing real software, we will need instances of some classes within other ones in order to achieve certain functionalities. In other words, once our building blocks are nicely separated, they would have dependencies in order to perform their functionality. What we are talking about here really boils down to the dependency injection. Self types provide a way to handle these dependencies in an elegant way.
