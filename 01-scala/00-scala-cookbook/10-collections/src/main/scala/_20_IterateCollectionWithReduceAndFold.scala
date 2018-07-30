/*
Walking Through a Collection with the reduce and fold Methods

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s21.html

The foldLeft method works like reduceLeft, you can seed value to be used for the first element.
The scanLeft walks through a sequence and returns a sequence instead of a single value.
 */
object _20_IterateCollectionWithReduceAndFold {
  val findMax = (x: Int, y: Int) => {
    val winner = x max y
    println(s"compared $x to $y, $winner was larger")
    winner
  }

  def main(args: Array[String]): Unit = {
    val vector = Vector(1, 4, 3, 2, 5)

    println("# vector.reduceLeft(_ + _)")
    println(vector.reduceLeft(_ + _))

    println("# vector.reduceLeft((x, y) => x + y) equivalent to reduceLeft(_ + _)")
    println(vector.reduceLeft((x, y) => x + y))

    println("# vector.reduceLeft(_ max _)")
    println(vector.reduceLeft(_ max _))

    println(vector.reduceLeft(findMax))

    println("# determine longest or shortest string")
    val customers = Vector("al", "hannah", "emily", "christina", "aleka")

    println("- longest string")
    println(customers.reduceLeft((x, y) => if (x.length > y.length) x else y))
    println("- shortest string")
    println(customers.reduceLeft((x, y) => if (x.length < y.length) x else y))

    println("# vector.foldLeft(10)(_ + _)")
    println(vector.foldLeft(10)(_ + _))

    println("# vector.scanLeft(10)(_ * _)")
    println(vector.scanLeft(10)(_ * _))
  }
}
/*
# vector.reduceLeft(_ + _)
15
# vector.reduceLeft((x, y) => x + y) equivalent to reduceLeft(_ + _)
15
# vector.reduceLeft(_ max _)
5
compared 1 to 4, 4 was larger
compared 4 to 3, 4 was larger
compared 4 to 2, 4 was larger
compared 4 to 5, 5 was larger
5
# determine longest or shortest string
- longest string
christina
- shortest string
al
# vector.foldLeft(10)(_ + _)
25
# vector.scanLeft(10)(_ * _)
Vector(10, 10, 40, 120, 240, 1200)
 */
