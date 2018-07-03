package monads.flatmap

/*
Using a list of Options with map and flatMap
 */
object OptionsList {
  def toInt(s: String): Option[Int] = {
    try {
      Some(Integer.parseInt(s.trim))
    }
    catch {
      case e: Exception => None
    }
  }

  def main(args: Array[String]): Unit = {
    val strings = Seq("1", "2", "foo", "3", "bar")

    println("# flatMap")
    println(strings)
    println(strings.map(toInt))
    println(strings.flatMap(toInt))
    println(strings.flatMap(toInt).sum)

    println("# map, then flatten")
    val mapResult = strings.map(toInt)
    println(mapResult)
    val flattenResult = mapResult.flatten
    println(flattenResult)
  }
}
/*
# flatMap
List(1, 2, foo, 3, bar)
List(Some(1), Some(2), None, Some(3), None)
List(1, 2, 3)
6
# map, then flatten
List(Some(1), Some(2), None, Some(3), None)
List(1, 2, 3)
 */
