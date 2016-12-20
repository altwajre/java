package com.company.app

object RightAssociativeColons extends App {
  class Foo(x:Int) {
    def ~:(y:Int) = x + y
  }

  val foo = new Foo(10)
  println(foo.~:(5))
  println(5 ~: foo) // Right Associative Colons
}

//$ scalac RightAssociativeColons.scala
//$ scala com.company.app.RightAssociativeColons
//15
//15
