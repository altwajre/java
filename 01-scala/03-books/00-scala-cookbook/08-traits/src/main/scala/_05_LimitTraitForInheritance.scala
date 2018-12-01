/*
Limiting Which Classes Can Use a Trait by Inheritance

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s06.html
 */
object _05_LimitTraitForInheritance {
  class StarfleetComponent
  trait StarfleetWarpCore extends StarfleetComponent
  class Starship extends StarfleetComponent with StarfleetWarpCore

  class RomulanStuff

  // below compile error: superclass RomulanStuff is not a subclass of the superclass StarfleetComponent
//  class WarBird extends RomulanStuff with StarfleetWarpCore

  def main(args: Array[String]): Unit = {
    val starship = new Starship
    println(starship.getClass.getSimpleName)
  }
}
