object FunctionReturnsFunction extends App {
  val g = (x:Int) => (y:Int) => x + y // function returns a function is also closure
  println(g(3)(5))
  println(g.apply(13).apply(5))
}

//$ scala FunctionReturnsFunction.scala
//8
//18
