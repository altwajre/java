/*
Replacements for ++ and −−

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch02s05.html

Scala does not have ++ and --
 */

object _04_IncrementAndDecrement {
  def main(args: Array[String]): Unit = {
    var a = 1
    a += 1
    println(a)

    var i = 2
    i *= 2
    println(i)
  }
}
/*
2
4
 */
