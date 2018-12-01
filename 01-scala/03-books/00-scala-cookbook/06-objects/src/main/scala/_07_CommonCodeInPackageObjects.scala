
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

package model {
  object CommonCodeInPackageObjects {
    def main(args: Array[String]): Unit = {

      // access model package objects
      echo("Hello")
      echo(MAGIC_NUM)
      echo(Margin.LEFT)

      // use
      var map = MutableMap("name" -> "Tom")
      map += ("password" -> "123")
      for((k, v) <- map) printf("key: %s, value: %s\n", k, v)
    }
  }
}
/*
Hello
42
LEFT
key: name, value: Tom
key: password, value: 123
 */
