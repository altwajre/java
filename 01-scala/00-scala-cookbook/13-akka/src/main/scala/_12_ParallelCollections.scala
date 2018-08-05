import scala.collection.parallel.immutable.ParVector

/*
Using Parallel Collections

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch13s13.html

Parallel collections improve performance
 */
object _12_ParallelCollections {
  def main(args: Array[String]): Unit = {
    println("# Vector")
    val vector = Vector.range(0, 10)
    vector.foreach(print)
    println("")

    vector.par.foreach(print)
    println("")

    println("# ParVector")
    val parVector = ParVector.range(0, 10)
    parVector.foreach(print)
  }
}
/*
# Vector
0123456789
0179863452
# ParVector
5760128349
 */
