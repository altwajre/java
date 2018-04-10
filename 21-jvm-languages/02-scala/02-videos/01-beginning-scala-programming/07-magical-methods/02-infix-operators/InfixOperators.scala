class Foo(x:Int) {
  def bar(y:Int) = x + y
  def baz(a:Int, b:Int) = x + a + b
}

object InfixOperatorsRunner extends App {
  val foo = new Foo(10)
  println(foo.bar(5))
  println(foo bar 5) // Infix invoke, invoke method with spaces
  println(foo.baz(10, 14))
  println(foo baz (10, 14)) // Infix invoke, invoke method with spaces
  println(3 + 10)
  println(3.+(10))
}

//$ scala InfixOperators.scala
//15
//15
//34
//34
//13
//13
