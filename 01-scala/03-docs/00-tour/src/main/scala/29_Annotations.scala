import scala.annotation.tailrec

/*
Annotations

https://docs.scala-lang.org/tour/annotations.html

Annotations associate meta-information with definitions.

https://en.wikipedia.org/wiki/Factorial

4! = 4 x 3 x 2 x 1 = 24

youtube - Recursion v Tail Recursion and Stack Frames

https://www.youtube.com/watch?v=wLRuAg0ZHt0
 */

case class Foo6()

object Annotations {
  def main(args: Array[String]) = {
    val result = factorial(4)
    println(result)
  }

  def factorial(x: Int): Int = {
    @tailrec
    def factorialHelper(x: Int, accumulator: Int): Int = {
      if (x == 1) accumulator else factorialHelper(x - 1, accumulator * x)
    }
    factorialHelper(x, 1)
  }
}
/*
24
 */
