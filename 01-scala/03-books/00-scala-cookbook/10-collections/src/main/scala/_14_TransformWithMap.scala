/*
Transforming One Collection to Another with map

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch10s15.html
 */
object _14_TransformWithMap {
  def main(args: Array[String]): Unit = {
    val customers = List("Tom", "Dick", "Harry")
    val elems = customers.map(c => s"<li>$c</li>")
    println(elems)
  }
}
/*
List(<li>Tom</li>, <li>Dick</li>, <li>Harry</li>)
 */
