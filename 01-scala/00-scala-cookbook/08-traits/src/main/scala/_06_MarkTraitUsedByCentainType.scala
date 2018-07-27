/*
Marking Traits So They Can Only Be Used by Subclasses of a Certain Type

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch08s07.html

 */
object _06_MarkTraitUsedByCentainType {
  trait StarfleetWarpCore {
    this: Starship => // marking traits only be used by subclass of Starship
  }
  class Starship
  class Enterprise extends Starship with StarfleetWarpCore

  def main(args: Array[String]): Unit = {
    val enterprise = new Enterprise
    println(enterprise.getClass.getSimpleName)
  }
}
/*
Enterprise
 */
