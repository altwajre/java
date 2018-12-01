/*
Flattening a List of Lists with flatten

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s16.html
 */
object _15_FlattenListOfLists {
  def main(args: Array[String]): Unit = {
    val lol = List(List(1, 2), List(3, 4))
    println(lol)

    val flatten = lol.flatten
    println(flatten)
  }
}
/*
List(List(1, 2), List(3, 4))
List(1, 2, 3, 4)
 */
