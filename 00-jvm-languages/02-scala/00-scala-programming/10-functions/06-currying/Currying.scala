object Currying extends App {
  val g = (x:Int) => (y:Int) => x + y // Currying - function returns function
  val f = (x:Int, y:Int) => x + y // simple function
  val fc = f.curried
  val f2 = Function.uncurried(fc)
  println(f2(3,5))
}

//$ scala Currying.scala
//8
