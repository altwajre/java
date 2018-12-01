/*
Accessing the Value of the Default Case in a Match Expression

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s11.html
 */
object _10_MatchDefaultCase {
  def main(args: Array[String]): Unit = {
    val i = 3
    i match {
      case 0 => println("1")
      case 1 => println("2")
      case default => println("default value: " + default)
    }
  }
}
/*
default value: 3
 */
