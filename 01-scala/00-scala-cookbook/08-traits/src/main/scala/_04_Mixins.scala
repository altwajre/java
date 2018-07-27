/*
Using Traits as Simple Mixins

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s05.html

multiple traits can be mixed into a class to provide a robust design

 */
object _04_Mixins {
  trait Tail {
    def wagTail: Unit = {
      println("tail is wagging")
    }
    def stopTail: Unit = {
      println("tail is stopped")
    }
  }
  abstract class Pet (var name: String) {
    def speak  // abstract
    def ownerIsHome: Unit = {
      println("excited")
    }
    def jumpForJoy: Unit = {
      println("jumping for joy")
    }
  }
  class Dog (name: String) extends Pet (name) with Tail {
    override def speak: Unit = println("woof")

    override def ownerIsHome: Unit = {
      wagTail
      speak
    }
  }
  def main(args: Array[String]): Unit = {
    val dog = new Dog("Rocky")
    dog.ownerIsHome
    dog.jumpForJoy
  }
}
/*
tail is wagging
woof
jumping for joy
 */
