
case class Foo()

object Basics {
  def main(args: Array[String]) = {
    println("# Expressions: computable statements")
    println(1 + 1)
    println("Hello, " + "world!")

    println("# Values: name results of expressions with `val` keyword")
    val x = 1 + 1
    println(s"val x = 1 + 1\nx = $x")

    println("# Blocks: combine expressions by surrounding {}")
    println({
      val x = 1 + 1
      1 + 1
    })

    println("# Functions: expressions take parameters")
    println("## Anonymous function")
    val addOne: Int => Int = (x: Int) => x + 1
    println(addOne(1))

    println("# Methods: similar to functions are defined with `def` keyword")

    def add(x: Int, y: Int): Int = x + y

    println(add(1, 2))

    println("# Classes: defined with `class` keyword; `Unit` means nothing to return")
    class Greeter(prefix: String, suffix: String) {
      def greet(name: String) = {
        println(prefix + name + suffix)
      }
    }
    val greeter = new Greeter("Hello, ", "!")
    greeter.greet("Scala")


    println("# Case Classes: immutable and compared by value")
    case class Point(x: Int, y: Int)
    val point = Point(1, 2)
    println(point)

    println("# Objects: single instances of their own definitions")
    object IdFactory {
      private var counter = 0

      def create(): Int = {
        counter += 1
        counter
      }
    }
    val id1 = IdFactory.create()
    println(id1)
    val id2 = IdFactory.create()
    println(id2)
  }
}

/*
# Expressions: computable statements
2
Hello, world!
# Values: name results of expressions with `val` keyword
val x = 1 + 1
x = 2
# Blocks: combine expressions by surrounding {}
2
# Functions: expressions take parameters
## Anonymous function
2
# Methods: similar to functions are defined with `def` keyword
3
# Classes: defined with `class` keyword; `Unit` means nothing to return
Hello, Scala!
# Case Classes: immutable and compared by value
Point(1,2)
# Objects: single instances of their own definitions
1
2
 */

object Basics2 {
  def main(args: Array[String]) = {
    println("# Traits: types containing certain fields and methods. Multiple traits can be combined")
    trait Greeter {
      def greet(name: String) = println("Hello, " + name)
    }

    class DefaultGreeter extends Greeter

    class CustomizableGreeter(prefix: String, postfix: String) extends Greeter {
      override def greet(name: String): Unit = {
        println(prefix + name + postfix)
      }
    }

    val greeter = new DefaultGreeter()
    greeter.greet("Default greeter")

    val customGreeter = new CustomizableGreeter("Custom Greeter say hi ", "!")
    customGreeter.greet("Scala")
  }
}

/*
# Traits: types containing certain fields and methods. Multiple traits can be combined
Hello, Default greeter
Custom Greeter say hi Scala!
 */
