
/*
Putting Common Code in Package Objects

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s08.html

You want to make functions, fields, and other code available at a package level, without requiring a class or object.
 */

package object model {
  // field
  val MAGIC_NUM = 42

  // method
  def echo(a: Any) {
    println(a)
  }

  // enumeration
  object Margin extends Enumeration {
    type Margin = Value
    val TOP, BOTTOM, LEFT, RIGHT = Value
  }

  // type definition
  type MutableMap[K, V] = Map[K, V]
  val MutableMap = Map
}

object CommonCodeInPackageObjects {
}
