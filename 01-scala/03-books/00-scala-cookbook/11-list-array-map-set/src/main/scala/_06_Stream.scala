/*
Using Stream, a Lazy Version of a List

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s07.html

Stream is a lazy version of a list.
The ? symbol is the way a lazy collection shows that the end of the collection hasn't been evaluated yet.

 */
object _06_Stream {
  def main(args: Array[String]): Unit = {
    val stream = (1 to 1000).toStream
    println(stream.head)
    println(stream.tail)
    println(stream.take(3))
    println(stream.filter(_ < 200))
    println(stream.map(_ * 2))
    println(stream.max)
    println(stream.size)
    println(stream.sum)
  }
}
/*
1
Stream(2, ?)
Stream(1, ?)
Stream(1, ?)
Stream(2, ?)
1000
1000
500500
 */
