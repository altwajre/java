package monads.flatmap

/*
Convert Map values to a sequence with flatMap
 */
object MapToFlatMap {
  def main(args: Array[String]) = {
    val map = Map(1 -> "one", 2 -> "two", 3 -> "three")
    println(map)
    val vector: IndexedSeq[String] = 1 to map.size flatMap (map.get)
    println(vector)
  }
}
/*
Map(1 -> one, 2 -> two, 3 -> three)
Vector(one, two, three)
 */
