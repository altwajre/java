/*
Sorting a Collection

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s29.html
 */
object _28_Sort {
  def sortByLength(s1: String, s2: String) = {
    println("comparing %s and %s".format(s1, s2))
    s1.length > s2.length
  }

  def main(args: Array[String]): Unit = {
    println("# List(10, 5, 8, 1, 7).sorted")
    println(List(10, 5, 8, 1, 7).sorted)

    println("""# List("Tom", "Dick", "Harry").sorted""")
    println(List("Tom", "Dick", "Harry").sorted)

    println("# ascending - List(10, 5, 8, 1, 7).sortWith(_ < _)")
    println(List(10, 5, 8, 1, 7).sortWith(_ < _))

    println("# descending - List(10, 5, 8, 1, 7).sortWith(_ > _)")
    println(List(10, 5, 8, 1, 7).sortWith(_ > _))

    println("""# List("Tom", "Dick", "Harry").sortWith(_ < _)""")
    println(List("Tom", "Dick", "Harry").sortWith(_ < _))

    println("""# List("Dick", "Tom", "Harry").sortWith(_.length < _.length)""")
    println(List("Dick", "Tom", "Harry").sortWith(_.length < _.length))

    println("""# List("Dick", "Tom", "Harry").sortWith(sortByLength)""")
    println(List("Dick", "Tom", "Harry").sortWith(sortByLength))
  }
}
/*
# List(10, 5, 8, 1, 7).sorted
List(1, 5, 7, 8, 10)
# List("Tom", "Dick", "Harry").sorted
List(Dick, Harry, Tom)
# ascending - List(10, 5, 8, 1, 7).sortWith(_ < _)
List(1, 5, 7, 8, 10)
# descending - List(10, 5, 8, 1, 7).sortWith(_ > _)
List(10, 8, 7, 5, 1)
# List("Tom", "Dick", "Harry").sortWith(_ < _)
List(Dick, Harry, Tom)
# List("Dick", "Tom", "Harry").sortWith(_.length < _.length)
List(Tom, Dick, Harry)
# List("Dick", "Tom", "Harry").sortWith(sortByLength)
comparing Tom and Dick
comparing Dick and Tom
comparing Harry and Tom
comparing Harry and Tom
comparing Harry and Dick
List(Harry, Dick, Tom)
 */
