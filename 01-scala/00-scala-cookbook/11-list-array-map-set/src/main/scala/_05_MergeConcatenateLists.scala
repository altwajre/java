/*
Merging (Concatenating) Lists

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s06.html
 */
object _05_MergeConcatenateLists {
  def main(args: Array[String]): Unit = {
    val a = List(1, 2, 3)
    val b = List(4, 5, 6)

    println("# ++")
    val c1 = a ++ b
    println(c1)

    println("# :::")
    val c2 = a ::: b
    println(c2)

    println("# concat()")
    val c3 = List.concat(a, b)
    println(c3)
  }
}
/*
# ++
List(1, 2, 3, 4, 5, 6)
# :::
List(1, 2, 3, 4, 5, 6)
# concat()
List(1, 2, 3, 4, 5, 6)
 */
