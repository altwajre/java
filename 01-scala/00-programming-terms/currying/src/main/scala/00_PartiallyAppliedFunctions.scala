/*
Partial functions are not partially applied functions

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/d1a89140-2a29-414b-a34f-00215af6ce81.xhtml

Note that these are not partially defined functions!

We can convert any multiple parameters function into curried function.
 */
object PartiallyAppliedFunctions {
  // normal function: assign function to variable
  val greaterOrEqual = (a: Int, b: Int) => a >= b
  val lessOrEqual = (a: Int, b: Int) => a <= b

  // multiple parameter lists
  def greaterOrEqualCurried(b: Int)(a: Int) = a >= b
  def lessOrEqualCurried(b: Int)(a: Int) = a <= b

  // curried function
  val greaterOrEqualCurriedVal: (Int) => (Int) => Boolean = b => a => a >= b
  val lessOrEqualCurriedVal: (Int) => (Int) => Boolean = b => a => a <= b
}

object PartiallyAppliedFunctionsApp {

  import PartiallyAppliedFunctions._

  val MAX = 20
  val MIN = 5

  def main(args: Array[String]): Unit = {
    val numbers = List(1, 5, 6, 11, 18, 19, 20, 21, 25, 30)
    // partially applied
    val ge = greaterOrEqual(_: Int, MIN)
    val le = lessOrEqual(_: Int, MAX)

    // curried
    val geCurried = greaterOrEqualCurried(MIN) _
    val leCurried = lessOrEqualCurried(MAX) _

    val geCurriedVal = greaterOrEqualCurriedVal(MIN)
    val leCurriedVal = lessOrEqualCurriedVal(MAX)

    println(s"Original list: $numbers")
    println(s"Normal function filtered list: ${numbers.filter(i => ge(i) && le(i))}")
    println(s"Multiple parameter lists filtered list: ${numbers.filter(i => geCurried(i) && leCurried(i))}")
    println(s"Curried function filtered list: ${numbers.filter(i => geCurriedVal(i) && leCurriedVal(i))}")

  }
}
/*
Original list: List(1, 5, 6, 11, 18, 19, 20, 21, 25, 30)
Normal function filtered list: List(5, 6, 11, 18, 19, 20)
Multiple parameter lists filtered list: List(5, 6, 11, 18, 19, 20)
Curried function filtered list: List(5, 6, 11, 18, 19, 20)
 */
