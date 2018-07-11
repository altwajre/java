/*
Partially defined functions- PartialFunction

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/489e7eed-5b89-4066-97d4-5da00caf6083.xhtml

Specific subsets of all possible values the functions can get.
 */
object PartiallyDefinedFunctions {
  val squareRoot: PartialFunction[Int, Double] = {
    case a if a >= 0 => Math.sqrt(a)
  }

  val square: PartialFunction[Int, Double] = {
    case a if a < 0 => Math.pow(a, 2)
  }
}

object PartiallyDefinedFunctionsApp {
  import PartiallyDefinedFunctions._

  def main(args: Array[String]): Unit = {
    val items = List(-1, 10, 11, -36, 36, -49, 49, 81)
    println(s"Can we calculate a root for -10: ${squareRoot.isDefinedAt(-10)}")
    println(s"Square roots: ${items.collect(square)}")
    println(s"Square roots or squares: ${items.collect(squareRoot.orElse(square))}")
  }
}
/*
Can we calculate a root for -10: false
Square roots: List(1.0, 1296.0, 2401.0)
Square roots or squares: List(1.0, 3.1622776601683795, 3.3166247903554, 1296.0, 6.0, 2401.0, 7.0, 9.0)
 */
