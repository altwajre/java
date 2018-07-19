# The pimp my library design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1ba5418d-0103-44d6-aaf4-d2611234f1b0.xhtml

similar to extension methods in C#
- It is implicit
- It extends AnyVal
implicit class StringExtensions(val s: String) extends AnyVal {}

It can be used when a decorator or adapter design pattern is needed.
