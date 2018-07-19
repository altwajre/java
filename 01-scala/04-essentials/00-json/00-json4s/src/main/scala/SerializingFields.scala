import org.json4s.FieldSerializer.{ignore, renameFrom, renameTo}
import org.json4s._
import native.Serialization._

object SerializingFields {
  abstract class Mammal {
    var name: String = ""
    var owner: Owner = null
    val size = List(10, 15)
  }

  class WildDog(val color: String) extends Mammal
  class WildCat(val cuteness: Int) extends Mammal
  case class Owner(name: String, age: Int)

  def main(args: Array[String]): Unit = {
    val dogSerializer = FieldSerializer[WildDog](
      renameTo("name", "animalname") orElse ignore("owner"),
      renameFrom("animalname", "name")
    )
    implicit val formats = DefaultFormats + dogSerializer

    val dog = new WildDog("black")
    dog.name = "pluto"
    dog.owner = Owner("joe", 35)
    println(s"${dog.getClass.getSimpleName}: ${dog.name}")

    println("# serialize")
    val ser = write(dog)
    println(s"${ser.getClass.getSimpleName}: $ser")

    println("# deserialize")
    val wildDog = read[WildDog](ser)
    println(s"${wildDog.getClass.getSimpleName}: ${wildDog.name}")
  }
}
/*
WildDog: pluto
# serialize
String: {"color":"black","animalname":"pluto","size":[10,15]}
# deserialize
WildDog: pluto
 */
