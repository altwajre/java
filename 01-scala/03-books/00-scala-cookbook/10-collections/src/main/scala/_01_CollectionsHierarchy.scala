/*
Understanding the Collections Hierarchy

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s02.html
 */
object _01_CollectionsHierarchy {
  def main(args: Array[String]): Unit = {
    println("# Vector")
    val v = Vector(1, 2, 3)
    println(v.sum)
    println(v.filter(_ > 1))
    println(v.map(_ * 2))

    println("# Sequence")
    val indexedSeq = IndexedSeq(1, 2, 3)
    println(indexedSeq)

    println("# Map")
    val map = Map(1 -> "a", 2 -> "b")
    println(map)

    println("# Set")
    val set = Set(1, 2, 3)
    println(set)
  }
}

/*
# Vector
6
Vector(2, 3)
Vector(2, 4, 6)
# Sequence
Vector(1, 2, 3)
# Map
Map(1 -> a, 2 -> b)
# Set
Set(1, 2, 3)
 */
