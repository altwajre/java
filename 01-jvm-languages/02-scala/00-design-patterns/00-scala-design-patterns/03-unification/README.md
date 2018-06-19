# Unification

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/fa09eeb1-4d3a-43f1-a30a-bb2740101e03.xhtml

- Functions and classes
- Algebraic data types and class hierarchies
- Modules and objects

## Increased expressivity

Unifying classes and functions leads to increased expressivity and we can easily achieve various things such as callbacks,
lazy parameter evaluation, centralized exception handling, and others, and without writing extra code and logic.
Moreover, functions as classes mean that we can extend them to provide extra functionality.

## Algebraic data types (ADTs)

Algebraic data types (ADTs) and class hierarchies are other unifications in the Scala programming language.
In other functional languages, there are special ways to create custom algebraic data types.
In Scala, this is achieved using class hierarchies and namely case classes and objects.

Algebraic data types are just composite types that combine other existing types or just represent some new ones.
They only have data and do not contain any functionality on top of this data as normal classes would.
Some examples can include the day of the week or a class that represents an RGB color - they have no extra functionality and they just carry information.

## The unification

It is obvious that class hierarchies and ADTs are unified and look like the same thing.
This adds a high level of flexibility in the language and makes modeling easier than other functional programming languages.

## Pattern matching

Pattern matching is often used with ADTs. It makes the code much clearer and more readable as well as easier to extend in comparison to using the if...else statements when trying to do something with ADTs based on their values. As you could imagine, these statements can get quite cumbersome in some cases, especially when there are many different possible values for a certain data type. In some cases, pattern matching can be used the same way as enums and the switch statement is used in Java.

> Pattern matching with values

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/dfa336eb-a91e-407e-ad9c-ac9ea7c24eea.xhtml

> Pattern matching for product ADTs

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/37cc288d-7e0a-4fea-addc-264fa9028b17.xhtml

Pattern matching shows its real power when used for product and hybird ADTs. In such cases, we can match the actual values of the data types.

When matching, we can ignore values we don't care about. For the area, we don't really need the position information.
The _ operator can be anywhere in the match statement, and it will just ignore the value it is put for.

## Modules and objects

Modules are a way to organize programs. They are interchangeable and pluggable pieces of code that have well-defined interfaces and hidden implementations. 
In Java, modules are organized in packages. In Scala, modules are objects, just like everything else. 
This means that they can be parameterized, extended, and passed as parameters, and so on.
