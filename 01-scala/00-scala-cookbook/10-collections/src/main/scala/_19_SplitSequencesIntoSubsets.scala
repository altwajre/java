/*
Splitting Sequences into Subsets (groupBy, partition, etc.)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s20.html
 */
object _19_SplitSequencesIntoSubsets {
  def main(args: Array[String]): Unit = {
    val list = List(15, 10, 5, 8, 20, 12)
    println("# list.groupBy(_ > 10)")
    val groups = list.groupBy(_ > 10)
    println(groups)
    println("- groups(true)")
    println(groups(true))
    println("- groups(false)")
    println(groups(false))
    println("# list.partition(_ > 10)")
    println(list.partition(_ > 10))
    println("# list.span(_ < 20)")
    println(list.span(_ < 20))
    println("# list.splitAt(2)")
    println(list.splitAt(2))

    println("# sliding breaks a sequence into many groups")
    val nums = (1 to 5).toList
    println("- size = 2")
    println(nums.sliding(2).toList)
    println("- size = 2, step = 2")
    println(nums.sliding(2, 2).toList)
    println("- size = 2, step = 3")
    println(nums.sliding(2, 3).toList)

    println("# unzip creates two lists out of a Tuples")
    val listOfTuple2s = List((1,2), ('a','b'))
    println(listOfTuple2s.unzip)

    println("# zip is opposite of unzip")
    val women = List("Kim", "Julia")
    val men = List("Al", "Terry")
    println(women zip men)
  }
}
/*
# list.groupBy(_ > 10)
Map(false -> List(10, 5, 8), true -> List(15, 20, 12))
- groups(true)
List(15, 20, 12)
- groups(false)
List(10, 5, 8)
# list.partition(_ > 10)
(List(15, 20, 12),List(10, 5, 8))
# list.span(_ < 20)
(List(15, 10, 5, 8),List(20, 12))
# list.splitAt(2)
(List(15, 10),List(5, 8, 20, 12))
# sliding breaks a sequence into many groups
- size = 2
List(List(1, 2), List(2, 3), List(3, 4), List(4, 5))
- size = 2, step = 2
List(List(1, 2), List(3, 4), List(5))
- size = 2, step = 3
List(List(1, 2), List(4, 5))
# unzip creates two lists out of a Tuples
(List(1, a),List(2, b))
# zip is opposite of unzip
List((Kim,Al), (Julia,Terry))
 */
