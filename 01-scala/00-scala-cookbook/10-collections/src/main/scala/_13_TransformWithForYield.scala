/*
Transforming One Collection to Another with for/yield

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s14.html
 */
object _13_TransformWithForYield {
  def main(args: Array[String]): Unit = {
    val customers = Vector("Tom", "Dick", "Harry")
    val tCustomers = for (c <- customers) yield (c, c.length)
    println(tCustomers)
  }
}
/*
Vector((Tom,3), (Dick,4), (Harry,5))
 */
