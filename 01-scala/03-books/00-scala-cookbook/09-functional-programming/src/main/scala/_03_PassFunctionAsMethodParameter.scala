/*
Defining a Method That Accepts a Simple Function Parameter

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s04.html

More Complex Functions

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s05.html

Define a method that takes a function as a parameter, and that function may have one or more input parameters, and may also return a value.
 */
object _03_PassFunctionAsMethodParameter {
  def executeFunction(callback: String => Int) = {
    callback("hello")
  }
  def main(args: Array[String]): Unit = {
    val stringLen = (s: String) => s.length
    println(executeFunction(stringLen))
  }
}
/*
Hello
 */
