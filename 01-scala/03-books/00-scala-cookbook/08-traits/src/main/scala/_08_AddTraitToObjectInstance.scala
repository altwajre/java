/*
Adding a Trait to an Object Instance

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s09.html

Rather than add a trait to an entire class, you just want to add a trait to an object instance when the object is created
 */
object _08_AddTraitToObjectInstance {
  class DavidBanner
  trait Angry {
    println("You won't like me...")
  }
  def main(args: Array[String]): Unit = {
    val hulk = new DavidBanner with Angry
  }
}
/*
You won't like me...
 */
