/*
Classes

https://docs.scala-lang.org/tour/classes.html

Classes are blueprints for creating objects.

 */

object DefineClass {
  def main(args: Array[String]) = {
    println("# Defining a class")
    class User
    val user = new User
    println(user.getClass)

    class Point(var x: Int, var y: Int) {
      def move(dx: Int, dy: Int) = {
        x = x + dx
        y = y + dy
      }

      override def toString: String = s"($x, $y)"
    }

    val point = new Point(2, 3)
    println(point)
  }
}

/*
# Defining a class
class DefineClass$User$1
(2, 3)
 */

object Constructors {
  def main(args: Array[String]) = {
    println("# Constructors: have optional parameters by providing default value")
    class Point(var x: Int = 0, var y: Int = 0)

    val point = new Point(2, 3)
    println(point.x)
  }
}

/*
# Constructors: have optional parameters by providing default value
2
 */


/*
- Private Members
the data is stored in private variable _x and _y.

- Getter
def x and def y for accessing the private data.

- Setter
def x_= and def y_= are for validating and setting the value of _x and _y.
Special syntax: the method has _= appended to the identifier to getter and the parameters come after.
 */
object PrivateMembers {
  def main(args: Array[String]) = {
    println("# Private Members and Getter/Setter Syntax")

    class Point {
      private var _x = 0
      private var _y = 0
      private val bound = 100

      def x = _x

      def x_=(newValue: Int) = {
        if (newValue < bound) _x = newValue else printWarning
      }

      def y = _y

      def y_=(newValue: Int) = {
        if (newValue < bound) _y = newValue else printWarning
      }

      private def printWarning = println("WARNING: Out of bounds")
    }

    val point = new Point
    point.x = 99
    println(point.x)
    point.y = 101
  }
}

/*
# Private Members and Getter/Setter Syntax
99
WARNING: Out of bounds
 */
