package monoids

import scalaz._
import Scalaz._

/*
Monoids in Scalaz

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/e880e942-444a-456c-ba71-0598f0ce7686.xhtml

addition, concatenation, reduce and fold; numbers.reduceLeft((a, b) => a + b
 */
object MonoidsApp {

  val stringConcatenation = new Monoid[String] {
    override def zero: String = ""

    override def append(f1: String, f2: => String): String = {
      f1 + f2
    }
  }

  def id[A](x: A): A = {
    x
  }

  def main(args: Array[String]): Unit = {
    val numbers = List(1, 2, 3, 4, 5)

    println("# numbers.foldMap(identity)")
    println(s"The sum is: ${numbers.foldMap(identity)}")
    println("# numbers.foldMap(id)")
    println(s"The sum is: ${numbers.foldMap(id)}")

    println("# numbers.foldMap(Tags.Multiplication.apply)")
    println(s"The product 5! is: ${numbers.foldMap(Tags.Multiplication.apply)}")

    println("# stringConcatenation")
    val strings = List("This is\n", "a list of\n", "strings!")
    println(strings.foldMap(id)(stringConcatenation))
  }
}
/*
# numbers.foldMap(identity)
The sum is: 15
# numbers.foldMap(id)
The sum is: 15
# numbers.foldMap(Tags.Multiplication.apply)
The product 5! is: 120
# stringConcatenation
This is
a list of
strings!
 */
