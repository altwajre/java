package monads.flatmap

/*
flatMap with another function

 */
object FlatMapFunction {
  def g(v: Int) = List(v-1, v, v+1)
  def main(args: Array[String]) = {
    val list = List(1, 2, 3, 4, 5)
    println(list)
    println(list.map(x => g(x)))
    println(list.flatMap(x => g(x)))
  }
}
/*
List(1, 2, 3, 4, 5)
List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), List(3, 4, 5), List(4, 5, 6))
List(0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6)
 */
