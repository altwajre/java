package functions

/*
Functions without syntactic sugar

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/58e297e3-5c9b-4fe3-9a27-13de94eb6c0b.xhtml

In the preceding example, we just used syntactic sugar. In order to understand exactly what happens, we will show you what the function literals are converted into.
They basically represent extensions to the FunctionN trait, where N is the number of parameters.
The implementations of the literals are invoked using the apply method.
 */
class SumFunction extends Function2[Int, Int, Int] {
  override def apply(v1: Int, v2: Int): Int = v1 + v2
}

class FunctionObjects {
  val sum = new SumFunction

  def runOperation(f: (Int, Int) => Int, a: Int, b: Int): Int = f(a, b)
}

object FunctionObjects {
  def main(args: Array[String]) = {
    val obj = new FunctionObjects
    println(s"3 + 9 = ${obj.sum(3, 9)}")
    println(s"Calling run operation: ${obj.runOperation(obj.sum, 10, 20)}")
  }
}
/*
3 + 9 = 12
Calling run operation: 30
 */
