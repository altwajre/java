/*
Using for Loops with Multiple Counters

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s03.html
 */
object _02_ForLoopsWithMultipleCounters {
  def main(args: Array[String]): Unit = {
    println("# Inline")
    for(i <- 1 to 2; j <- 1 to 2) println(s"i = $i, j = $j")

    println("# Multiline")
    for {
      i <- 1 to 2
      j <- 1 to 2
    } println(s"i = $i, j = $j")

//    for {
//      i <- 1 to 2
//      j <- 1 to 3
//      k <- 1 to 4
//    } println(s"i = $i, j = $j, k = $k")

    println("# custom two-dimensional array")
    val array = Array.ofDim[Int](2,2)
    array(0)(0) = 0
    array(0)(1) = 1
    array(1)(0) = 2
    array(1)(1) = 3

    for {
      i <- 0 to 1
      j <- 0 to 1
    } println(s"($i)($j) = ${array(i)(j)}")
  }
}
/*
# Inline
i = 1, j = 1
i = 1, j = 2
i = 2, j = 1
i = 2, j = 2
# Multiline
i = 1, j = 1
i = 1, j = 2
i = 2, j = 1
i = 2, j = 2
# custom two-dimensional array
(0)(0) = 0
(0)(1) = 1
(1)(0) = 2
(1)(1) = 3
 */
