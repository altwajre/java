/*
Defining a Private Primary Constructor

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s05.html

Singleton pattern
 */
object _04_Singleton {
  class Brain private {
    override def toString: String = "This is the brain."
  }
  object Brain {
    val brain = new Brain
    def getInstance = brain
  }
  def main(args: Array[String]): Unit = {
    val brain = Brain.getInstance
    println(brain)
  }
}
/*
This is the brain.
 */
