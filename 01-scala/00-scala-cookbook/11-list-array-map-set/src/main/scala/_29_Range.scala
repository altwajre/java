import scala.collection.mutable.ArrayBuffer

/*
Using a Range

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s30.html
 */
object RangeApp {
  def main(args: Array[String]): Unit = {
    println("# 1 to 5")
    println(1 to 5)

    println("# 1 until 5")
    println(1 until 5)

    println("# 1 to 5 by 2")
    println(1 to 5 by 2)

    println("# (1 to 5).toList")
    println((1 to 5).toList)

    println("# (1 to 5).toArray")
    println((1 to 5).toArray.mkString(", "))

    println("# (1 to 5).toSet")
    println((1 to 5).toSet)

    println("# Array.range(1, 5)")
    println(Array.range(1, 5).mkString(", "))

    println("# Vector.range(1, 5)")
    println(Vector.range(1, 5))

    println("# List.range(1, 5)")
    println(List.range(1, 5))

    println("# ArrayBuffer.range('a', 'd')")
    println(ArrayBuffer.range('a', 'd'))
  }
}
/*
# 1 to 5
Range 1 to 5
# 1 until 5
Range 1 until 5
# 1 to 5 by 2
Range 1 to 5 by 2
# (1 to 5).toList
List(1, 2, 3, 4, 5)
# (1 to 5).toArray
1, 2, 3, 4, 5
# (1 to 5).toSet
Set(5, 1, 2, 3, 4)
# Array.range(1, 5)
1, 2, 3, 4
# Vector.range(1, 5)
Vector(1, 2, 3, 4)
# List.range(1, 5)
List(1, 2, 3, 4)
# ArrayBuffer.range('a', 'd')
ArrayBuffer(a, b, c)
 */
