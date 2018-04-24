object FunctionsWithFunctions extends App { // runnable
//  val f:(Int, Int => Int) => Int = (x:Int, y:Int => Int) => y(x)
  val f = (x:Int, y:Int => Int) => y(x)
//  println(f(3, (m:Int) => m + 1))
//  println(f(3, _ + 1)) // _ is like groovy it
  println(f(3, m => m + 1))
}

//$ scala FunctionsWithFunctions.scala
//4
