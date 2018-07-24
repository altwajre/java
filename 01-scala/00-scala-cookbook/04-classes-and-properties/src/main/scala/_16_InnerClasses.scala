import scala.collection.mutable.ArrayBuffer

/*
Creating Inner Classes

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04s17.html

Keep the class out of public API, or encapsulate the code
 */
object _16_InnerClasses {
  class PandorasBox {
    case class Thing (name: String)

    var things = new ArrayBuffer[Thing]()
    things += Thing("Thing #1")
    things += Thing("Thing #2")

    def addThing(name: String): Unit = {
      things += new Thing(name)
    }
  }

  def main(args: Array[String]): Unit = {
    val p = new PandorasBox
    p.things.foreach(println)
  }
}
/*
Thing(Thing #1)
Thing(Thing #2)
 */
