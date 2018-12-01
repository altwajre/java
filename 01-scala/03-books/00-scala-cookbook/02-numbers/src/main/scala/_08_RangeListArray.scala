/*
Creating a Range, List, or Array of Numbers

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch02s09.html
 */
object _08_RangeListArray {
  def main(args: Array[String]): Unit = {
    println("# 1 to 3")
    val r1 = 1 to 3
    println(r1.foreach(println))

    println("# 1 to 5 by 2")
    val r2 = 1 to 5 by 2
    println(r2.foreach(println))

    println("# for loops")
    for (i <- 1 to 3) println(i)

    println("# range with until")
    for (i <- 1 until 3) println(i)
  }
}
/*
# 1 to 3
1
2
3
()
# 1 to 5 by 2
1
3
5
()
# for loops
1
2
3
# range with until
1
2
 */
