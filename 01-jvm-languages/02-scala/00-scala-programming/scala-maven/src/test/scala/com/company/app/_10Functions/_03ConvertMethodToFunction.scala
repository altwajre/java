package com.company.app._10Functions

class Foo(x:Int) {
  def bar(y:Int) = x + y
  def gym(z:Int, a:Int) = x + z + a
}

class Baz(y:Int) {
  // a method takes function as parameter
  // pointer to a function; pass Int, return Int
  def qux(f:Int => Int) = f(y) // qux(f:Int => Int) means function as parameter in a method
  def jam(f:(Int, Int) => Int) = f(y, 10)
}

object ConvertMethodToFunction extends App {
  val x = new Foo(3)
  val f = x.bar _ // convert a method to a function by using _
  // 5 + 3 = 8
  println(f.apply(5))
  // 8
  println(f(5))
  // 8

  // 10 + 3 = 13
  val z = new Baz(10)
  // pass Foo.bar() method as function into Baz.qux(f)
  println(z.qux(f))
  // 13
  println(z.qux(x.bar))
  // 13

  // 100 + 10 + 3 = 113
  val f2 = x.gym(100, _:Int)
  println(z.qux(f2))
  // 113

  // 10 + 10 + 3 = 23
  val f3 = x.gym _
  println(z.jam(f3))
  // 23
}
