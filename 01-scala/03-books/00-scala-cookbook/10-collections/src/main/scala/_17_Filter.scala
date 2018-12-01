/*
Using filter to Filter a Collection

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s18.html
 */
object _17_Filter {
  def main(args: Array[String]): Unit = {
    val x = List.range(1, 10)
    val evens = x.filter(_ % 2 == 0)
    println(evens)
  }
}
/*
List(2, 4, 6, 8)
 */
