# Scala Design Patterns

https://www.scala-lang.org/old/sites/default/files/FrederikThesis.pdf

http://www.lihaoyi.com/post/OldDesignPatternsinScala.html

## Rules of inheritance hierarchies

- In Java, even if a class does not explicitly extend another one, its superclass will be java.lang.Object.
The same stands for Scala, and the equivalent base is AnyRef.

- There is a similarity between directly extending a trait and extending the trait superclass and mixing the trait in using the with keyword.
