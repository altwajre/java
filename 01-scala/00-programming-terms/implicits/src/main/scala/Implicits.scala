/*
Scala Implicits

https://www.youtube.com/watch?v=wMaLe4NuOS4
*/
case class HiString(s: String) {
  def sayHi() = {
    println("Hi, my name is " + s)
  }
}

object ImplicitConversion extends App {
  val name: String = "Mark"

  // Implicit conversion: convert string to HiString
  implicit def argString(s: String): HiString = HiString(s)

  name.sayHi()
}
/*
Hi, my name is Mark
 */

object ImplicitParameter extends App {
  def doItFewTimes(numTimes: Int)(implicit hiString: HiString) = {
    for (i <- 1 to numTimes) {
      hiString.sayHi()
    }
  }

  implicit val a = new HiString("Tom")

  doItFewTimes(3)
}
/*
Hi, my name is Tom
Hi, my name is Tom
Hi, my name is Tom
 */
