/*
Converting a Collection to a String with mkString

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s30.html
 */
object _29_mkString {
  def main(args: Array[String]): Unit = {
    println(List("Tom", "Dick", "Harry").mkString)
    println(List("Tom", "Dick", "Harry").mkString(" "))
    println(List("Tom", "Dick", "Harry").mkString(", "))

    println(List(List("a", "b"), List("C", "d")).flatten.mkString(", "))
  }
}
/*
TomDickHarry
Tom Dick Harry
Tom, Dick, Harry
a, b, C, d
 */
