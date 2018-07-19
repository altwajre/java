import java.util.Date

import org.json4s._
import org.json4s.jackson.Serialization.{read, write}

object SerializationJackson {
  implicit val formats = DefaultFormats

  case class Child(name: String, age: Int, birthdate: Option[Date])

  def main(args: Array[String]): Unit = {
    println("# serialize")
    val serStr = write(Child("Mary", 5, None))
    println(s"${serStr.getClass.getSimpleName}: $serStr")

    println("# deserialize")
    val deSer = read[Child](serStr)
    println(s"${deSer.getClass.getSimpleName}: $deSer")
  }
}
/*
# serialize
String: {"name":"Mary","age":5}
# deserialize
Child: Child(Mary,5,None)
 */
