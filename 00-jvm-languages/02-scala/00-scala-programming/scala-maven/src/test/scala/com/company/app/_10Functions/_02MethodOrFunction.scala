package com.company.app._10Functions

object MyObject {
  // takes string, returns int
  val f = (x:String) => x.size // function: f is a reference to function
  // takes string, return int
  def m(x:String) = x.size // method: m is object inside MyObject accepts an Int
}

object MethodOrFunction extends App {
  // function: can use apply() to invoke the function
  println(MyObject.f.apply("function"))
  // 8
  println(MyObject.f("function"))
  // 8
  println(MyObject.m("method"))
  // 6
}
