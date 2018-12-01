/*
Using Partially Applied Functions

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s07.html

Eliminate repetitively passing variables into a function by
a) passing common variables into the function
b) create a new function that is preloaded with those values
c) use the new function, passing it only the unique variables it needs
 */
object _06_PartiallyAppliedFunctions {
  def main(args: Array[String]): Unit = {
    val sum = (a: Int, b: Int, c: Int) => a + b + c

    val f = sum(1, 2, _: Int)
    println(f(3))
  }
}
/*
6
 */
