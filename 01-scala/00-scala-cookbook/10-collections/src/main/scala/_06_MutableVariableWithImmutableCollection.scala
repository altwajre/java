/*
Understanding Mutable Variables with Immutable Collections

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s07.html

customers variable points to a new collection each time you use the :+ method.
the customers variable is mutable
it's actually being reassigned to a new collection during each step.
 */
object _06_MutableVariableWithImmutableCollection {
  def main(args: Array[String]): Unit = {
    var customers = Vector("Tom")
    customers = customers :+ "Dick"
    customers = customers :+ "Harry"
    customers.foreach(println)
  }
}
/*
Tom
Dick
Harry
 */
