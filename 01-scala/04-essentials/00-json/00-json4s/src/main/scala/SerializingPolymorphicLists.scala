import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.{read, write}

object SerializingPolymorphicLists {
  implicit val formats = Serialization.formats(ShortTypeHints(List(classOf[Dog], classOf[Fish])))

  trait Animal
  case class Dog(name: String) extends Animal
  case class Fish(weight: Double) extends Animal
  case class Animals(animals: List[Animal])

  def main(args: Array[String]): Unit = {
    println("# serialize")
    val serStr = write(Animals(Dog("pluto") :: Fish(1.2) :: Nil))
    println(s"${serStr.getClass.getSimpleName}: $serStr")

    println("# deserialize")
    val animals = read[Animals](serStr)
    println(s"${animals.getClass.getSimpleName}: $animals")
  }
}
/*
# serialize
String: {"animals":[{"jsonClass":"SerializingPolymorphicLists$Dog","name":"pluto"},{"jsonClass":"SerializingPolymorphicLists$Fish","weight":1.2}]}
# deserialize
Animals: Animals(List(Dog(pluto), Fish(1.2)))
 */
