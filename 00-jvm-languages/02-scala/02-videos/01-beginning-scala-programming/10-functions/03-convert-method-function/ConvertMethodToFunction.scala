class Foo(x:Int) {
  def bar(y:Int) = x + y
  def gym(z:Int, a:Int) = x + z + a
}

class Baz(y:Int) {
  // a method takes function as parameter
  def qux(f:Int => Int) = f(y)  // qux(f:Int => Int) means function as parameter in a method
  def jam(f:(Int, Int) => Int) = f(y, 10)
}

object ConvertMethodToFunction extends App {
  val x = new Foo(3)
  val f = x.bar _ // convert a method to a function by using _
  println(f.apply(5))
  println(f(15))

  val z = new Baz(25)
  // pass Foo.bar() method as function into Baz.qux(f)
  println(z.qux(f))
  println(z.qux(x.bar))

  val f2 = x.gym(100, _:Int)
  println(z.qux(f2))
}

//$ scala ConvertMethodToFunction.scala
//8
//18
//28
//28
//128
