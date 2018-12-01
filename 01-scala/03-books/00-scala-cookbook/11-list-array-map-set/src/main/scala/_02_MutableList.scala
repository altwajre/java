import scala.collection.mutable.ListBuffer

/*
Creating a Mutable List

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s03.html
 */
object _02_MutableList {
  def main(args: Array[String]): Unit = {
    val customers = new ListBuffer[String]()

    println("# add one element at a time")
    customers += "Lee"
    customers += "Tom"
    println(customers)

    println("# add multiple elements")
    customers += ("Dick", "Harry", "Jen")
    println(customers)

    println("# remove one element")
    customers -= "Lee"
    println(customers)

    println("# remove multiple elements")
    customers -= ("Dick", "Harry")
    println(customers)

    println(customers.toList)
  }
}
/*
# add one element at a time
ListBuffer(Lee, Tom)
# add multiple elements
ListBuffer(Lee, Tom, Dick, Harry, Jen)
# remove one element
ListBuffer(Tom, Dick, Harry, Jen)
# remove multiple elements
ListBuffer(Tom, Jen)
List(Tom, Jen)
 */
