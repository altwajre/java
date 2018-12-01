/*
Adding Elements to a List

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s04.html
 */
object _03_AddElementToList {
  def main(args: Array[String]): Unit = {
    println("# List(2)")
    val x = List(2)
    println(x)
    val y = 1 :: x
    println(y)

    println("# 2 :: Nil")
    val a = 2 :: Nil
    println(a)
    val b = 1 :: a
    println(b)
  }
}
/*
List(2)
List(1, 2)
List(2)
List(1, 2)
 */
