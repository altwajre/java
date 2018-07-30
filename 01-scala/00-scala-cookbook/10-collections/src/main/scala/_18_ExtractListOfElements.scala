/*
Extracting a Sequence of Elements from a Collection

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s19.html
 */
object _18_ExtractListOfElements {
  def main(args: Array[String]): Unit = {
    val list = (1 to 10).toList
    println(list)

    println("# list.drop(3)")
    println(list.drop(3))

    println("# list.dropWhile(_ < 6)")
    println(list.dropWhile(_ < 6))

    println("# list.dropRight(4)")
    println(list.dropRight(4))

    println("# list.take(3)")
    println(list.take(3))

    println("# list.takeWhile(_ < 5)")
    println(list.takeWhile(_ < 5))

    println("# list.takeRight(3)")
    println(list.takeRight(3))

    println("# slice(from, until)")
    println(list.slice(1, 3))
  }
}
/*
List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
# list.drop(3)
List(4, 5, 6, 7, 8, 9, 10)
# list.dropWhile(_ < 6)
List(6, 7, 8, 9, 10)
# list.dropRight(4)
List(1, 2, 3, 4, 5, 6)
# list.take(3)
List(1, 2, 3)
# list.takeWhile(_ < 5)
List(1, 2, 3, 4)
# list.takeRight(3)
List(8, 9, 10)
# slice(from, until)
List(2, 3)
 */
