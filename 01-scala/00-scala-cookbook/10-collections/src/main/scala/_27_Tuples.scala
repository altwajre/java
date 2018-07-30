/*
Tuples, for When You Just Need a Bag of Things

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s28.html
 */
object _27_Tuples {
  case class Person(name: String)
  def main(args: Array[String]): Unit = {
    println("""# ("Debi", 28)""")
    println(("Debi", 28))

    println("""# (3, "Three", new Person("Tom"))""")
    val tuple3 = (3, "Three", new Person("Tom"))
    println(tuple3)
    println(s"tuple3._1=${tuple3._1}, tuple3._2=${tuple3._2}, tuple3._3=${tuple3._3}")
  }
}
/*
# ("Debi", 28)
(Debi,28)
# (3, "Three", new Person("Tom"))
(3,Three,Person(Tom))
tuple3._1=3, tuple3._2=Three, tuple3._3=Person(Tom)
 */
