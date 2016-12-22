object CurriedParameters extends App {
  def foo(x:Int, y:Int, z:Int) = x + y + z
  def bar(x:Int)(y:Int)(z:Int) = x + y + z
  def baz(x:Int, y:Int)(z:Int) = x + y + z

  val f = foo(10, _:Int, _:Int) // partial function
  val g = bar(20) _ // partial function

  println(f(3, 5))
  println(g(5)(3))
  println(g.apply(5).apply(3))
}

//$ scala CurriedParameters.scala
//18
//28
//28
