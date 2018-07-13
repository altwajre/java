package _08_implicits

/*
Implicit conversions

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/02953dc8-c7d2-405f-979b-25a48eee1a15.xhtml

implicits can be used for silent conversions.
 */
object ImplicitsConversions {

  implicit def doubleToInt(a: Double): Int =
    Math.round(a).toInt

  implicit def intsToString(ints: List[Int]): String =
    ints.map(_.toChar).mkString

  def main(args: Array[String]): Unit = {
    val number: Int = 7.6
    println(s"The integer value for 7.6 is $number")

    // prints HELLO!
    printAsciiString(List(72, 69, 76, 76, 79, 33))
  }

  def printAsciiString(s: String): Unit = {
    println(s)
  }
}
/*
The integer value for 7.6 is 8
HELLO!
 */
