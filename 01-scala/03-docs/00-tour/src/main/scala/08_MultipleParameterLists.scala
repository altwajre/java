/*
Multiple Parameter Lists (currying)

https://docs.scala-lang.org/tour/multiple-parameter-lists.html

 */

object MultipleParameterLists {
  def main(args: Array[String]) = {
    val numbers = List(1,2,3)
    val res = numbers.foldLeft(0)((m, n) => m + n)
    println(res)
  }
}
/*
6
 */
