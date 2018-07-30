/*
Creating a Lazy View on a Collection

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s25.html

Create a "lazy" version of a large collection, so it will only compute and return results as they are actually needed.
 */
object _24_LazyView {
  def main(args: Array[String]): Unit = {
    println("# (1 to 10).view")
    println((1 to 10).view)

    println("# (1 to 10).view.force")
    println((1 to 10).view.force)

    println("# (1 to 10).map(_ * 2)")
    println((1 to 10).map(_ * 2))

    println("# (1 to 10).view.map(_ * 2)")
    println((1 to 10).view.map(_ * 2))
  }
}
