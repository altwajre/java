/*
Operators

https://docs.scala-lang.org/tour/operators.html

((a + b) ^? (c ?^ d)) less ((a ==> b) | c)
?^ has highest precedence because it starts with character ?. + has second highest precedence, followed by ^?, ==>, |, less
 */

case class Bar6()

object Operators {
  def main(args: Array[String]) = {
    println(10.+(1))

    case class Vec(val x: Double, val y: Double) {
      // Any method with a single parameter can be used as an operator
      def +(that: Vec) = new Vec(this.x + that.x, this.y + that.y)
    }

    val vector1 = Vec(1.0, 1.0)
    val vector2 = Vec(2.0, 2.0)

    val vector3 = vector1 + vector2
    println(vector3.x)
    println(vector3.y)
  }
}
/*
11
3.0
3.0
 */
