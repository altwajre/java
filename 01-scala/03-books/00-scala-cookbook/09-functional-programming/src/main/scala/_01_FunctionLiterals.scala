/*
Using Function Literals (Anonymous Functions)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s02.html

Anonymous function aka function literal - so you can pass it into a method that takes a function, or assign it to variable
 */
object _01_FunctionLiterals {
  def main(args: Array[String]): Unit = {
    val x = List.range(1, 10)
    val anonymousFunction: Int => Boolean = (i: Int) => i % 2 == 0
    // pass anonymous-function to List.filter()
    val evens = x.filter(anonymousFunction)
    println(evens)
  }
}
/*
List(2, 4, 6, 8)
 */
