class Foo(x:Int) {
  def apply(y:Int) = x + y
}

object MagicApply extends App {
  val foo = new Foo(10)
  println(foo.apply(20))
  // when call apply(), you can leave the method name `apply`
  println(foo(21))
}
