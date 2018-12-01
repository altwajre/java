import scala.collection.mutable

/*
Using a Stack

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch11s29.html
 */
object StackApp {
  def main(args: Array[String]): Unit = {
    var customers = mutable.Stack[String]()

    println("- add one element")
    customers.push("Tom")
    println(customers)

    println("- add multiple elements")
    customers.push("Dick", "Harry")
    println(customers)

    println("- .pop()")
    println(customers.pop)
    println(customers)

    println("- peek: .top()")
    println(customers.top)
    println(customers)

    println("- .size()")
    println(customers.size)

    println("- .isEmpty")
    println(customers.isEmpty)

    println("- .clear")
    println(customers.clear)
  }
}
/*
- add one element
Stack(Tom)
- add multiple elements
Stack(Harry, Dick, Tom)
- .pop()
Harry
Stack(Dick, Tom)
- peek: .top()
Dick
Stack(Dick, Tom)
- .size()
2
- .isEmpty
false
- .clear
()
 */
