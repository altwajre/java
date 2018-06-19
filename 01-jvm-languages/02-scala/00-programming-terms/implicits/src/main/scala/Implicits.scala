case class HiString(s: String) {
  def sayHi() = {
    println("Hi, my name is " + s)
  }
}

object Implicits extends App {
  val name: String = "Mark"

  // convert string to HiString
  implicit def argString(s: String): HiString = HiString(s)

  name.sayHi()

}

/*
Hi, my name is Mark
 */
