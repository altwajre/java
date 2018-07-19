object MyObject {
  val f = (x:String) => x.size // function: f is a reference to function
  def m(x:String) = x.size // method: m is object inside MyObject accepts an Int
}

object MethodOrFunction extends App {
  println(MyObject.f.apply("function")) // function can use apply to invoke the function
  // 8
  println(MyObject.f("function"))
  // 8
  println(MyObject.m("method"))
  // 6
}
