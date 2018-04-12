object MyObject {
  val f = (x:String) => x.size // function
  def m(x:String) = x.size // method
}

object MethodOrFunction extends App {
  println(MyObject.f.apply("function")) // apply invoke the function
  println(MyObject.m("method"))
}
