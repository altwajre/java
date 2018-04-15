package com.company.app._10Functions

object Functions1 extends App{
  // Function1 takes string; returns int
  var f1:Function1[String, Int] = new Function[String, Int] {
    override def apply(x: String): Int = x.length
  }
  println(f1("Hello"))
  // 5

  // Function0 return int
  val f0:Function0[Int] = new Function0[Int] {
    override def apply(): Int = 1
  }
  println(f0.apply())
  // 1
  println(f0())
  // 1

  // Function2 takes int, string; returns string
  val f2:Function2[Int, String, String] = new Function2[Int, String, String] {
    override def apply(v1: Int, v2: String): String = v1 + v2
  }
  println(f2(8, "Wow"))
  // 8Wow
}

object Functions2 extends App {
  // Function1 takes string; returns int
  var f1:(String => Int) = new Function[String, Int] {
    override def apply(x: String): Int = x.length
  }
  println(f1("Hello"))
  // 5

  // Function0 return int
  val f0:(() => Int) = new Function0[Int] {
    override def apply(): Int = 1
  }
  println(f0.apply())
  // 1
  println(f0())
  // 1

  // Function2 takes int, string; returns string
  val f2:((Int, String) => String) = new Function2[Int, String, String] {
    override def apply(v1: Int, v2: String): String = v1 + v2
  }
  println(f2(8, "Wow"))
  // 8Wow
}

object Functions3 extends App {
  // Function1 takes string; returns int
  var f1:String => Int = new Function[String, Int] {
    override def apply(x: String): Int = x.length
  }
  println(f1("Hello"))
  // 5

  // Function0 return int
  val f0:() => Int = new Function0[Int] {
    override def apply(): Int = 1
  }
  println(f0.apply())
  // 1
  println(f0())
  // 1

  // Function2 takes int, string; returns string
  val f2:(Int, String) => String = new Function2[Int, String, String] {
    override def apply(v1: Int, v2: String): String = v1 + v2
  }
  println(f2(8, "Wow"))
  // 8Wow
}

object ShortHandFunctions extends App {
  // Function1 takes string; returns int
  var f1 = (s:String) => s.length
  println(f1("Hello"))
  // 5

  // Function0 return int
  val f0 = () => 1
  println(f0.apply())
  // 1
  println(f0())
  // 1

  // Function2 takes int, string; returns string
  val f2 = (v1:Int, v2:String) => v1 + v2
  println(f2(8, "Wow"))
  // 8Wow
}

object ReturnMultipleResultsFunction extends App {
  val multipleResults = (x:String) => (x, x.size)
  println(multipleResults("Hello"))
  // (Hello,5)
}
