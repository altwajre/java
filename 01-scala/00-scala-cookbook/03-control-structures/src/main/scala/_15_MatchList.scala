/*
Working with a List in a Match Expression

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s16.html
 */
object _15_MatchList {
  def main(args: Array[String]): Unit = {
    println("# listHeadTail")
    val y = 1 :: 2 :: 3 :: Nil
    listHeadTail(y)

    println("# listToString")
    val fruits = "Apples" :: "Bananas" :: "Oranges" :: Nil
    val listStr = listToString(fruits)
    println(listStr)

    println("# sum")
    val nums = List(1, 2, 3, 4, 5)
    println(s"sum of List(1, 2, 3, 4, 5): ${sum(nums)}")

  }

  def listHeadTail(list: List[Int]): Unit = list match {
    case head :: tail => println(s"head: $head, tail: $tail")
    case Nil => println("empty")
  }

  def listToString(list: List[String]): String = list match {
    case s :: rest => s + " " + listToString(rest)
    case Nil => ""
  }

  def sum(list: List[Int]): Int = list match {
    case Nil => 0
    case n :: rest => n + sum(rest)
  }
}
/*
# listHeadTail
head: 1, tail: List(2, 3)
# listToString
Apples Bananas Oranges
# sum
sum of List(1, 2, 3, 4, 5): 15
 */
