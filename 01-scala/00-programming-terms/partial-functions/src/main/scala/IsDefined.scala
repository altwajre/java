/*
PartialFunction

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

 */
object isDefinedAtApp {
  // explicitly state that the function is defined when the input parameter is not zero
  val divide = new PartialFunction[Int, Int] {
    def apply(x: Int): Int = 42 / x
    def isDefinedAt(x: Int): Boolean = x != 0
  }

  def main(args: Array[String]) = {
    if(divide.isDefinedAt(1)) {
      println(divide(1))
    }

    if(divide.isDefinedAt(0)) {
      println(divide(0))
    }
    else {
      println("divide by zero is not allowed")
    }
  }
}
/*
42
divide by zero is not allowed
 */

object caseApp {
  val divide: PartialFunction[Int, Int] = {
    case d: Int if d != 0 => 42 / d
  }
  def main(args: Array[String]) = {
    if(divide.isDefinedAt(1)) {
      println(divide(1))
    }

    if(divide.isDefinedAt(0)) {
      println(divide(0))
    }
    else {
      println("divide by zero is not allowed")
    }

  }
}
/*
42
divide by zero is not allowed
 */


