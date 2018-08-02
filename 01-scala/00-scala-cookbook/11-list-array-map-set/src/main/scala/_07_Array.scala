import scala.util.Sorting

/*
Different Ways to Create and Update an Array

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s08.html

Sorting Arrays
https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s11.html
 */
object ArrayApp {
  def main(args: Array[String]): Unit = {
    println("# Array(1, 2, 3)")
    println(Array(1, 2, 3).mkString(", "))
    println("""# Array("Tom", "Dick", "Harry")""")
    println(Array("Tom", "Dick", "Harry").mkString(", "))

    println("# Array(1, 2.0, 33D, 400L)")
    println(Array(1, 2.0, 33D, 400L).mkString(", "))

    println("# new Array[String](3)")
    val customers = new Array[String](3)
    customers(0) = "Tom"
    customers(1) = "Dick"
    customers(2) = "Harry"
    println(customers.mkString(", "))

    println("# Array.range(1, 5)")
    println(Array.range(1, 5).mkString(", "))

    println("# Array.range(0, 5, 2)")
    println(Array.range(0, 5, 2).mkString(", "))

    println("""# Array.fill(3)("foo")""")
    println(Array.fill(3)("foo").mkString(", "))

    println("# Array.tabulate(5)(n => n * n)")
    println(Array.tabulate(5)(n => n * n).mkString(", "))

    println("# List(1, 2, 3).toArray")
    println(List(1, 2, 3).toArray.mkString(", "))

    println("""# "Hello".toArray""")
    println("Hello".toArray.mkString(", "))

    println("# Sort array")
    val fruits = Array("cherry", "apple", "banana")
    Sorting.quickSort(fruits)
    println(fruits.mkString(", "))
  }
}
/*
# Array(1, 2, 3)
1, 2, 3
# Array("Tom", "Dick", "Harry")
Tom, Dick, Harry
# Array(1, 2.0, 33D, 400L)
1.0, 2.0, 33.0, 400.0
# new Array[String](3)
Tom, Dick, Harry
# Array.range(1, 5)
1, 2, 3, 4
# Array.range(0, 5, 2)
0, 2, 4
# Array.fill(3)("foo")
foo, foo, foo
# Array.tabulate(5)(n => n * n)
0, 1, 4, 9, 16
# List(1, 2, 3).toArray
1, 2, 3
# "Hello".toArray
H, e, l, l, o
# Sort array
apple, banana, cherry
 */
