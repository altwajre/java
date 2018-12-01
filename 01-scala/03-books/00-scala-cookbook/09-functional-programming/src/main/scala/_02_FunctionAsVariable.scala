/*
Using Functions as Variables

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch09s03.html
 */
object _02_FunctionAsVariable {
  def main(args: Array[String]): Unit = {
    val double: Int => Int = (i: Int) => {
      i * 2
    }
    println(double(3))
  }
}
/*
6
 */
