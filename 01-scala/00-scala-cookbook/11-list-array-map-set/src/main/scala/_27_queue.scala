import scala.collection.mutable

/*
Using a Queue

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s28.html
 */
object _27_queue {
  def main(args: Array[String]): Unit = {
    var queue = new mutable.Queue[String]
    println("- add one element")
    queue += "Tom"
    println(queue)

    println("- add multiple elements")
    queue += ("Dick", "Harry")
    println(queue)

    println("- add add multiple elements in another Seq")
    queue ++= List("Lee", "Jen")
    println(queue)

    println("- .enqueue()")
    queue.enqueue("Al")
    println(queue)

    println("- .dequeue")
    val next = queue.dequeue
    println(next)
    println(queue)

    println("""- .dequeueFirst(_.startsWith("L"))""")
    println(queue.dequeueFirst(_.startsWith("L")))
    println(queue)

    println("- .dequeueAll(_.length > 4)")
    println(queue.dequeueAll(_.length > 4))
    println(queue)
  }
}
/*
- add one element
Queue(Tom)
- add multiple elements
Queue(Tom, Dick, Harry)
- add add multiple elements in another Seq
Queue(Tom, Dick, Harry, Lee, Jen)
- .enqueue()
Queue(Tom, Dick, Harry, Lee, Jen, Al)
- .dequeue
Tom
Queue(Dick, Harry, Lee, Jen, Al)
- .dequeueFirst(_.startsWith("L"))
Some(Lee)
Queue(Dick, Harry, Jen, Al)
- .dequeueAll(_.length > 4)
ArrayBuffer(Harry)
Queue(Dick, Jen, Al)
 */
