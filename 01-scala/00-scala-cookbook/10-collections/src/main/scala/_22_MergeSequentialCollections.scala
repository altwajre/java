import scala.collection.mutable.ArrayBuffer

/*
Merging Sequential Collections

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s23.html

- Use the ++= method to merge a sequence into a mutable sequence
- Use the ++ method to merge two mutable or immutable sequences
- Use collection methods like union, diff and interest
 */
object _22_MergeSequentialCollections {
  def main(args: Array[String]): Unit = {
    println("Use ++= method to merge a sequence")
    val arrayBuffer = ArrayBuffer(1, 2, 3)
    arrayBuffer ++= Seq(4, 5, 6)
    println(arrayBuffer)

    println("Use ++ method to merge two mutable or immutable collections")
    val list1 = List(1, 2, 3, 4)
    val list2 = List(3, 4, 5, 6)
    val list3 = list1 ++ list2
    println(list3)

    println("# list1.intersect(list2)")
    println(list1.intersect(list2))

    println("# list1.union(list2)")
    println(list1.union(list2))

    println("# list1.union(list2).distinct")
    println(list1.union(list2).distinct)

    println("# list1 diff list2")
    println(list1 diff list2)

    println("# List.concat(list1, list2)")
    println(List.concat(list1, list2))

    println("# list1 ::: list2 - prepends the elements of one list to another")
    println(list1 ::: list2)
  }
}
/*
Use ++= method to merge a sequence
ArrayBuffer(1, 2, 3, 4, 5, 6)
Use ++ method to merge two mutable or immutable collections
List(1, 2, 3, 4, 3, 4, 5, 6)
# list1.intersect(list2)
List(3, 4)
# list1.union(list2)
List(1, 2, 3, 4, 3, 4, 5, 6)
# list1.union(list2).distinct
List(1, 2, 3, 4, 5, 6)
# list1 diff list2
List(1, 2)
# List.concat(list1, list2)
List(1, 2, 3, 4, 3, 4, 5, 6)
# list1 ::: list2
List(1, 2, 3, 4, 3, 4, 5, 6)
 */
