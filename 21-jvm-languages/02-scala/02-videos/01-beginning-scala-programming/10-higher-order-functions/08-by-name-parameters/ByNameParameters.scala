object ByNameParameters extends App {
  def byValue(x:Int)(y:Int) = {
    println("By Value:")
    x + y
  }

  def byFunction(x:Int)(y: () => Int) = {
    println("By Function:")
    x + y()
  }

  def byName(x:Int)(y: => Int) = { // (y: => Int) - by-name parameter
    println("By Name:")
    x + y
  }

  val a = byValue(3){ // eager evaluation
    println("In call")
    18
  }
//  In call <- first
//  By Value:

  val b = byFunction(3)(() => { // lazy evaluation, but not clean call
    println("In call")
    19
  })
//  By Function:
//  In call <- later

  val c = byName(3){ // lazy evaluation and clean call
    println("In call")
    19
  }
//  By Name:
//  In call <- later

}
//
//$ scala ByNameParameters.scala
//In call
//By Value:
//By Function:
//In call
//By Name:
//In call
