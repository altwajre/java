object TuplesApp {
  def main(args: Array[String]): Unit = {
    commaSeparated

    arrowSeparated()
  }

  private def commaSeparated = {
    println("# commaSeparated")
    val t = (8, "Cool", 3.14)
    println(s"${t.getClass.getSimpleName}: $t")
    println(s"t._1: ${t._1}")
    println(s"t._2: ${t._2}")
    println(s"t._3: ${t._3}")
  }

  private def arrowSeparated() = {
    println("# arrowSeparated()")
    val t = ("name" -> "joe")
    println(s"${t.getClass.getSimpleName}: $t")
    println(s"t._1: ${t._1}")
    println(s"t._2: ${t._2}")
  }

}
/*
# commaSeparated
Tuple3: (8,Cool,3.14)
t._1: 8
t._2: Cool
t._3: 3.14
# arrowSeparated()
Tuple2: (name,joe)
t._1: name
t._2: joe
 */
