/*
Create Function Currying With Parameter Groups

https://lukajcb.github.io/blog/scala/2016/03/08/a-real-world-currying-example.html

http://allaboutscala.com/tutorials/chapter-3-beginner-tutorial-using-functions-scala/scala-tutorial-create-function-currying-parameter-groups

https://www.youtube.com/watch?v=c3I8yOD3JPA&t=223s

Partially Applied Functions and Currying
 */

object ParameterGroups {
  // the second group of parameter is lower end bound
  def add(a: Int)(b: Int): Int = a + b

  def getStringLength(s: String)(f: (Int) => Int): Int = {
    f(s.length) // function
  }

  def main(args: Array[String]): Unit = {
    println("# def add(a: Int)(b: Int): Int = a + b")
    val onePlusFive = add(1)(5)
    println(onePlusFive)

    val addFour = add(4)_ // (Int => Int)
    val twoPlusFour = addFour(2)
    println(twoPlusFour)

    println("# pass function as parameter with parentheses")
    val a = getStringLength("hello") (r => r * 2)
    println(a)

    println("# pass function as parameter with curly braces")
    val b = getStringLength("hello") {
      val f: Int => Int = (r: Int) => r * 2 // function
      f // pass function
    }
    println(b)

  }
}
/*
# def add(a: Int)(b: Int): Int = a + b
6
6
# pass function as parameter with parentheses
10
# pass function as parameter with curly braces
10
 */

case class Bar()
