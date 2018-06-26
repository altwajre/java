/*
Nested Methods

https://docs.scala-lang.org/tour/nested-functions.html
 */

case class Bar()

object NestedMethods {
  def main(args: Array[String]) = {
    def factorial(x: Int): Int = {
      def fact(x: Int, accumulator: Int): Int = {
        println(s"accumulator: $accumulator")
        if(x <= 1) {
          accumulator
        }
        else {
          println(s"fact(): x=$x")
          fact(x - 1, x * accumulator)
        }
      }
      fact(x, 1)
    }
    println(s"Factorial of 2: ${factorial(4)}")
  }
}
/*
accumulator: 1
fact(): x=4
accumulator: 4
fact(): x=3
accumulator: 12
fact(): x=2
accumulator: 24
Factorial of 2: 24
 */
