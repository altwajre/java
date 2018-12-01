/*
Different Ways to Create and Populate a List

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s02.html
 */
object ListApp {
  def main(args: Array[String]): Unit = {
    println("# 1 :: 2 :: 3 :: Nil")
    println(1 :: 2 :: 3 :: Nil)

    println("# List(1, 2, 3)")
    println(List(1, 2, 3))

    println("# List(1, 2.0, 33D, 400L)")
    println(List(1, 2.0, 33D, 400L))

    println("# List.range(1, 5)")
    println(List.range(1, 5))

    println("# List.range(1, 5, 2)")
    println(List.range(1, 5, 2))

    println("""# List.fill(3)("foo")""")
    println(List.fill(3)("foo"))

    println("# List.tabulate(5)(n => n * n)")
    println(List.tabulate(5)(n => n * n))

    println("# collection.mutable.ListBuffer(1, 2, 3).toList")
    println(collection.mutable.ListBuffer(1, 2, 3).toList)

    println("""# "foo".toList""")
    println("foo".toList)
  }
}
/*
# 1 :: 2 :: 3 :: Nil
List(1, 2, 3)
# List(1, 2, 3)
List(1, 2, 3)
# List(1, 2.0, 33D, 400L)
List(1.0, 2.0, 33.0, 400.0)
# List.range(1, 5)
List(1, 2, 3, 4)
# List.range(1, 5, 2)
List(1, 3)
# List.fill(3)("foo")
List(foo, foo, foo)
# List.tabulate(5)(n => n * n)
List(0, 1, 4, 9, 16)
# collection.mutable.ListBuffer(1, 2, 3).toList
List(1, 2, 3)
# "foo".toList
List(f, o, o)
 */
