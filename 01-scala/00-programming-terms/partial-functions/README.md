# PartialFunction

https://www.scala-lang.org/api/2.12.1/scala/PartialFunction.html

A partial function of type PartialFunction[A, B] is unary function where the domain does not necessarily include all values of type A.
The function isDefinedAt allows to test dynamically if a value is in the domain of the function.

https://www.youtube.com/watch?v=wZUcf68vw40&t=150s

How to create and use Partial Functions in Scala

division is partial function

https://alvinalexander.com/scala/how-to-define-use-partial-functions-in-scala-syntax-examples

Problem:

Define a Scala function that will only work for a subset of possible input values,
or you want to define a series of functions that only work for a subset of input values,
and combine those functions to completely solve a problem.

Solution:

A partial function is a function that does not provide an answer for every possible input value it can be given.
It provides an answer only a subset of possible data, and defines the data it can handle.
A partial function can be queried to determine if it can handle a particular value.
