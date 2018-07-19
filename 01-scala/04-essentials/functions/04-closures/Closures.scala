class Foo(x:Int) {
  // a method takes function as parameter
  def bar(y:Int => Int) = y(x) // function as parameter in a method
}

object Closures extends App {
  val m = 200 // environment
  val f = (x:Int) => x + m // we are closing our environment including m=200 that m is coming from outside of the function
  val foo = new Foo(100)
  // 100 + 200
  println(foo.bar(f))
}

//$ scala Closures.scala
//300
