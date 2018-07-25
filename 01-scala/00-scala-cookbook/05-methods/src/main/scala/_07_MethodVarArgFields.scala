/*
Creating Methods That Take Variable-Argument Fields

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch05s08.html

varargs fields: add * after the field type
 */
object _07_MethodVarArgFields {
  def printAll(strings: String*): Unit = {
    strings.foreach(print)
    println
  }

  def main(args: Array[String]): Unit = {
    printAll()
    printAll("foo")
    printAll("foo,", "bar")
    printAll("foo,", "bar,", "baz")

    println("# _* to adapt a sequence")
    val fruits = List("apple", "banana", "cherry")
    printAll(fruits: _*)
  }
}
/*

foo
foo,bar
foo,bar,baz
# _* to adapt a sequence
applebananacherry
 */
