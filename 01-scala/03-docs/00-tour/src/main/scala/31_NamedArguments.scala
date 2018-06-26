/*
Named Arguments

https://docs.scala-lang.org/tour/named-arguments.html

 */

case class Foo8()

object NamedArguments {
  def main(args: Array[String]) = {
    printName("John", "Smith")
    printName(first = "Tom", last = "Lee")
    printName(last="Lee", first = "Tom")
  }

  def printName(first: String, last: String): Unit = {
    println(first + " " + last)
  }
}
/*
John Smith
Tom Lee
Tom Lee
 */
