/*
Ensuring a Trait Can Only Be Added to a Type That Has a Specific Method

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s08.html

Use a variation of the self-type syntax to declare that any class that attempts to mix in the trait must implement the method

The WarpCore trait requires that any classes that attempt to mix it in must have an ejectWarpCore method.
 */
object _07_MixinTypeHasSpecificMethod {

  trait WarpCore {
    this: {
      def ejectWarpCore(password: String): Boolean
      def starWarpCore: Unit
    } =>
  }

  class Starship

  class Enterprise extends Starship with WarpCore {
    def ejectWarpCore(password: String): Boolean = {
      if (password == "password") {
        println("core ejected")
        true
      }
      else false
    }
    def starWarpCore: Unit = {
      println("core started")
    }
  }

  def main(args: Array[String]): Unit = {
    val enterprise = new Enterprise
    enterprise.ejectWarpCore("password")
    enterprise.starWarpCore
  }
}
/*
core ejected
core started
 */
