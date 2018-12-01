/*
Creating a Function That Returns a Function

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s08.html

Return a function (algorithm) from a function method
 */
object _07_FunctionReturnsFunction {
  def saySomething(prefix: String) = (s: String) => {
    prefix + " " + s
  }
  def main(args: Array[String]): Unit = {
    val sayHello = saySomething("Hello")
    println(s"sayHello: ${sayHello.getClass.getSimpleName}")
    println(sayHello("Tom"))
  }
}
/*
sayHello: FunctionReturnsFunction$$$Lambda$1/824909230
Hello Tom
 */
