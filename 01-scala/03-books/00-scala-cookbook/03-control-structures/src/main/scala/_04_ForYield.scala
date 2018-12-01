import scala.collection.mutable.ArrayBuffer

/*
Creating a for Comprehension (for/yield Combination)

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch03s05.html
 */
object _04_ForYield {
  def main(args: Array[String]): Unit = {
    val names = Array("chris", "ed", "maurice")

    println("# Inline")
    val capNames = for (e <- names) yield e.capitalize
    capNames.foreach(println)

    println("# Multilines")
    val lengths = for (e <- names) yield  {
      // multiple lines
      e.length
    }
    lengths.foreach(println)

    println("# List")
    val fruits = "apple" :: "banana" :: "orange" :: Nil

    val out = for (e <- fruits) yield e.toUpperCase

    println(out)

    arrayBuffer

  }

  private def arrayBuffer = {
    println("# arrayBuffer")

    var fruits = ArrayBuffer[String]()
    fruits += "apple"
    fruits += "banana"
    fruits += "orange"

    val out = for (e <- fruits) yield e.toUpperCase
    println(out)
  }
}
/*
# Inline
Chris
Ed
Maurice
# Multilines
5
2
7
# List
List(APPLE, BANANA, ORANGE)
# arrayBuffer
ArrayBuffer(APPLE, BANANA, ORANGE)
 */
