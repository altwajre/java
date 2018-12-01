/*
Make Vector Your “Go To” Immutable Sequence

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s08.html

The Vector class is the "go to" general purpose immutable data structure.
 */
object _07_VectorImmutable {
  def main(args: Array[String]): Unit = {
    println("# Create")
    val a = Vector(1, 2, 3)
    println(a)
    println(a(0))

    println("# Append and assign the result to a new variable")
    val b = a ++ Vector(4, 5)
    println(b)

    println("# Update and assign the result to a new variable")
    val c = b.updated(0, 8)
    println(c)

    println("# Filtering - take first 2")
    val d = c.take(2)
    println(d)

    println("# Filtering - take bigger than 2")
    val e = c.filter(_ > 2)
    println(e)
  }
}
/*
# Create
Vector(1, 2, 3)
1
# Append and assign the result to a new variable
Vector(1, 2, 3, 4, 5)
# Update and assign the result to a new variable
Vector(8, 2, 3, 4, 5)
# Filtering - take first 2
Vector(8, 2)
# Filtering - take bigger than 2
Vector(8, 3, 4, 5)
 */
