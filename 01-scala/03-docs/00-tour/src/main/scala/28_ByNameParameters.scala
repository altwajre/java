/*
By-name Parameters

https://docs.scala-lang.org/tour/by-name-parameters.html

By-name parameters are only evaluated when used.
This ability to delay evaluation of a parameter until it is used can help performance
 */

case class Bar7()

object ByNameParameters {
  def whileLoop(condition: => Boolean)(body: => Unit): Unit = {
    if(condition) {
      body
      whileLoop(condition)(body)
    }
  }

  def main(args: Array[String]) = {
    var i = 2
    whileLoop(i > 0) {
      println(i)
      i -= 1
    }
  }
}
/*
2
1
 */
