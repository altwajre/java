// $ scalac Closures.scala
// $ scala Closures
//- Closures are functions that close around the environment
//- Closures are used to make up functions from the environment
//- It is best to enclose around something that is stable, e.g. val
class Foo2(x:Int) {
  // a method takes function as parameter
  def bar(y:Int => Int) = y(x)
}

object Closures extends App {
  val m = 200 // environment to be closed
  val f = (x:Int) => x + m // closing environment m=200 comes from outside of function
  val foo = new Foo2(100)
  // 100 + 200
  println(foo.bar(f))
  // 300
}
