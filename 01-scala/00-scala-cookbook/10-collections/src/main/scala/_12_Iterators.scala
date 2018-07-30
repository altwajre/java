/*
Using Iterators

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s13.html
 */
object _12_Iterators {
  def main(args: Array[String]): Unit = {
    val it = Iterator(1, 2, 3)
    it.foreach(println)
  }
}
/*
1
2
3
 */
