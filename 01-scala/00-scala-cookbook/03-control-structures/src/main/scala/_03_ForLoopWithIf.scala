/*
Using a for Loop with Embedded if Statements (Guards)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s04.html
 */
object _03_ForLoopWithIf {
  def main(args: Array[String]): Unit = {
    println("# Inline")
    for(i <- 1 to 5 if i % 2 == 0) println(i)

    println("# Multilines")
    for {
      i <- 1 to 5
      if i % 2 == 0
    } println(i)
  }
}
/*
# Inline
2
4
# Multilines
2
4
 */
