/*
Extracting Unique Elements from a Sequence

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s22.html
 */
object _21_ExtractUniqueElements {
  def main(args: Array[String]): Unit = {
    val vector = Vector(1, 1, 2, 3, 3, 4)

    println("# vector.distinct")
    println(vector.distinct)

    println("# vector.toSet")
    println(vector.toSet)
  }
}
/*
# vector.distinct
Vector(1, 2, 3, 4)
# vector.toSet
Set(1, 2, 3, 4)
 */
