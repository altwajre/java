object DivideSafely extends App {
  def divideSafely(f: => Int):Option[Int] = {
    try{
      Some(f)
    } catch {
      case ae:ArithmeticException => None
    }
  }

  val d = divideSafely {
    val x = 10
    val y = 5
    x / y
  }
  println(d.get)

  val e = divideSafely {
    val x = 10
    val y = 0
    x / y
  }
  println(e)
}

//$ scala DivideSafely.scala
//2
//None
