/*
Currying

https://docs.scala-lang.org/tour/currying.html
 */

object PartialFunctionApp {
  def filter(list: List[Int], func: Int => Boolean): List[Int] = {
    if(list.isEmpty) list
    else if (func(list.head)) {
      val head = list.head
      val tail = list.tail
      println(s"else if: head=$head, tail=$tail")
      head :: filter(tail, func)
    }
    else {
      val tail = list.tail
      println(s"else: tail=$tail")
      filter(tail, func)
    }
  }

  def modN(n: Int)(x:Int): Boolean = ((x % n ) == 0)

  def main(args: Array[String]): Unit = {
    val nums = List(1, 2, 3, 4, 5, 6)
    println(filter(nums, modN(2)))
  }
}
/*
else: tail=List(2, 3, 4, 5, 6)
else if: head=2, tail=List(3, 4, 5, 6)
else: tail=List(4, 5, 6)
else if: head=4, tail=List(5, 6)
else: tail=List(6)
else if: head=6, tail=List()
List(2, 4, 6)
 */

/*
https://www.youtube.com/watch?v=9qknm7YhQDk

Return function
Method takes one arg and returns a function
 */
object Currying {
  def add(a: Int): Int => Int = {
    (b: Int) => a + b
  }

  def main(args: Array[String]): Unit = {
    val addTwo = add(2)
    val twoPlusThree = addTwo(3)
    println(twoPlusThree)

    val getStringLength: String => Int = (s: String) => s.length
    val length = getStringLength("hello, world.")
    println(length)
  }
}
/*
5
13
 */
