/*
Using a Trait as an Interface

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s02.html
 */
object _01_Interface {
  trait Person {
    def raiseHand
    def say(msg: String)
  }
  class Student extends Person {
    override def raiseHand: Unit = println("Raise Hand")

    override def say(msg: String): Unit = println(s"Say $msg")
  }
  def main(args: Array[String]): Unit = {
    val student = new Student
    student.raiseHand
    student.say("Hello")
  }
}
/*
Raise Hand
Say Hello
 */
