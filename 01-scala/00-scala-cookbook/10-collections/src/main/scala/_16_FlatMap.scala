/*
Combining map and flatten with flatMap

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s17.html

use map to transform integer String into Int

- Using map (or a for/yield expression) to create a new collection from an existing collection
- The resulting collection is a list of lists
- You can flatten immediately after map (or a for/yield expression)
 */
object _16_FlatMap {
  def toInt(in: String): Option[Int] = {
    try {
      Some(Integer.parseInt(in.trim))
    }
    catch {
      case e: Exception => None
    }
  }
  def main(args: Array[String]): Unit = {
    val lol = List("1", "Tom", "2", "Dick", "4", "Harry")

    println("# use map to transform String into Int, and returns Some[Int]")
    println(lol.map(toInt))

    println("# use flatten to extract the values from Some elements")
    println(lol.map(toInt).flatten)

    println("# flatMap")
    val map = lol.flatMap(toInt)
    println(map)
    val sum = map.sum
    println(sum)
  }
}
/*
# use map to transform String into Int, and returns Some[Int]
List(Some(1), None, Some(2), None, Some(4), None)
# use flatten to extract the values from Some elements
List(1, 2, 4)
# flatMap
List(1, 2, 4)
7
 */
