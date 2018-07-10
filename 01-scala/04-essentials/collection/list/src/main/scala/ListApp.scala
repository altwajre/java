/*
List

https://www.youtube.com/watch?v=HVweO8aSqKU

 */
object ListApp {
  def main(args: Array[String]): Unit = {
    println("# Add item to empty list")
    val emptyList = List.empty
    val list1 = 1 :: emptyList
    println(list1)

    println("# Add item to list")
    val list12 = 1 :: 2 :: Nil
    println(list12)
    val list123 = 3 :: list12
    println(list123)

    println("# for - iterating items")
    for (i <- list123) {
      println(i)
    }

    println("# foreach - with temp var _ ")
    list123.foreach {
      println(_)
    }

    println("# foreach - i * 2 ")
    list123.foreach(i => {
      println(i * 2)
    })
  }
}
/*
# Add item to empty list
List(1)
# Add item to list
List(1, 2)
List(3, 1, 2)
# for - iterating items
3
1
2
# foreach - with temp var _
3
1
2
# foreach - i * 2
6
2
4
 */
