# Traits vs classes

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/34ad008e-bb3a-4e48-b093-713d7f67cf9c.xhtml

> Use classes:

- When a behavior is not going to be reused at all or in multiple places
- When you plan to use your Scala code from another language, for example, if you are building a library that could be used in Java

> Use traits:

- When a behavior is going to be reused in multiple unrelated classes.
- When you want to define interfaces and want to use them outside Scala, for example, Java. Because traits do not have any implementations are complied similar to interfaces.
