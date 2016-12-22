object Functions extends App { // it is runnable
//  val f1:Function1[String, Int] = new Function1[String, Int] {
//    def apply(s:String):Int = s.length
//  }
  val f1 = (s:String) => s.length

//  val f0:Function0[Int] = new Function0[Int] {
//    def apply() = 8
//  }
  val f0 = () => 8

//  val f2:Function2[Int, String, String] = new Function2[Int, String, String] {
//    def apply(x:Int, y:String) = y + x
//  }
//
  val f2 = (x:Int, y:String) => y + x

  val f3 = (x:String) => (x, x.size) // returns Tuples

//  println(f1.apply("Hello"))
  println(f1("Hello"))
  println(f0())
  println(f2(8, "Wow"))
  println(f3("Hello"))

}

//$ scala Functions.scala
//5
