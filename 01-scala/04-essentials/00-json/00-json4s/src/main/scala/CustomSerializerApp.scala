import org.json4s.JsonAST.{JNull, JString}
import org.json4s.native.Serialization._
import org.json4s.{CustomSerializer, DefaultFormats, Formats, native}

object CustomSerializerApp {
  sealed trait Breed
  case object Beagle extends Breed
  case object Mastiff extends Breed
  case object Yorkie extends Breed

  case class Dog(name: String, breed: Breed)

  case object BreedSerializer extends CustomSerializer[Breed](format => (
    {
      case JString(breed) =>  breed match {
        case "Beagle" => Beagle
        case "Mastiff" => Mastiff
        case "Yorkie" => Yorkie
      }
      case JNull => null
    },
    {
      case breed:Breed => {
        println("# BreedSerializer: " + breed.getClass.getSimpleName)
        JString(breed.getClass.getSimpleName.replace("$",""))
      }
    }))

  def main(args: Array[String]): Unit = {
    val serializers = List(BreedSerializer)
    implicit lazy val serializerFormats: Formats = DefaultFormats ++ serializers

    val dog = new Dog("Tom", Mastiff)
    println("# Dog=" + dog)

    val ser = write(dog)
    println(s"# main: ${ser.getClass.getSimpleName}: $ser")
  }
}
/*
Dog(Tom,Mastiff)
Mastiff$
String: {"name":"Tom","breed":"Mastiff"}
 */
