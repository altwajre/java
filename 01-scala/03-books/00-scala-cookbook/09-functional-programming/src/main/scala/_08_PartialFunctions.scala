/*
Creating Partial Functions

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s09.html

Function will only work for a subset of possible input values

For example: divide by zero is not allowed
 */
object _08_PartialFunctions {
  def main(args: Array[String]): Unit = {
    println("# override")
    val divide = new PartialFunction[Int, Int] {
      override def isDefinedAt(x: Int): Boolean = x != 0

      override def apply(x: Int): Int = 6 / x
    }

    val result = if(divide.isDefinedAt(2)) divide(2)
    println(result)

    println("# case")
    val divide2: PartialFunction[Int, Int] = {
      case d: Int if d != 0 => 6 / d
    }

    val result2 = if(divide2.isDefinedAt(3)) divide2(3)
    println(result2)
  }
}
/*
# override
3
# case
2
 */
