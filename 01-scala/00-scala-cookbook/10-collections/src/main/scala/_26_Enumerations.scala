/*
Creating and Using Enumerations

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s27.html
 */
object _26_Enumerations {
  object Margin extends Enumeration {
    type Margin = Value
    val TOP, BOTTOM, LEFT, RIGHT = Value
  }
  def main(args: Array[String]): Unit = {
    val currentMargin = Margin.TOP

    if(currentMargin == Margin.TOP) println("Working on Top")

    println("# print all the enumeration values")
    Margin.values foreach println
  }
}
/*
Working on Top
# print all the enumeration values
TOP
BOTTOM
LEFT
RIGHT
 */
