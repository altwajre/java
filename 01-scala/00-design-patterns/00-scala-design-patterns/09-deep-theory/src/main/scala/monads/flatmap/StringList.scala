package monads.flatmap

/*
Using flatMap on a list of Strings
 */
object StringList {
  def main(args: Array[String]): Unit = {
    val fruits = Seq("apple", "banana", "orange")

    println("# flatMap")
    println(fruits.map(_.toUpperCase))
    // flatMap flattens the list of strings into a sequence of characters(Seq[Char])
    println(fruits.flatMap(_.toUpperCase))

    println("# map, then flatten")
    val mapResult = fruits.map(_.toUpperCase)
    println(mapResult)
    val flattenResult = mapResult.flatten
    println(flattenResult)

    println(Seq("abc", "xyz").flatMap(_.toUpperCase()))
  }
}
/*
# flatMap
List(APPLE, BANANA, ORANGE)
List(A, P, P, L, E, B, A, N, A, N, A, O, R, A, N, G, E)
# map, then flatten
List(APPLE, BANANA, ORANGE)
List(A, P, P, L, E, B, A, N, A, N, A, O, R, A, N, G, E)
 */
