/*
Populating a Collection with a Range

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s26.html

 */
object _25_PopulateCollectionWithRange {
  def main(args: Array[String]): Unit = {
    println("# List.range(1, 3)")
    println(List.range(1, 3))

    println("# Vector.range(1, 5, 2)")
    println(Vector.range(1, 5, 2))

    println("# 1 to 5 by 2 toList")
    println(1 to 5 by 2 toList)

    println("# (0 until 5 by 2).toSet")
    println((0 until 5 by 2).toSet)

    println("# ('a' to 'f').toList")
    println(('a' to 'f').toList)

    println("# ('a' to 'f').by(2).toList")
    println(('a' to 'f').by(2).toList)
  }
}
/*
# List.range(1, 3)
List(1, 2)
# Vector.range(1, 5, 2)
Vector(1, 3)
# 1 to 5 by 2 toList
List(1, 3, 5)
# (0 until 5 by 2).toSet
Set(0, 2, 4)
# ('a' to 'f').toList
List(a, b, c, d, e, f)
# ('a' to 'f').by(2).toList
List(a, c, e)
 */
