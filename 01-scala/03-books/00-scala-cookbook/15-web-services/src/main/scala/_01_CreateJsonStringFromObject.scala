import com.google.gson.Gson
import net.liftweb.json.DefaultFormats
import net.liftweb.json.Serialization.write

/*
Creating a JSON String from a Scala Object

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch15s02.html
 */

/*
https://github.com/lift/framework/tree/master/core/json
 */
object LiftJsonApp {
  case class Person(name: String, address: Address)
  case class Address(city: String, state: String)

  def main(args: Array[String]): Unit = {
    val person = Person("Tom", Address("Talkeetna", "AK"))

    // create a JSON string from the Person, then print it
    implicit val formats = DefaultFormats
    val jsonString = write(person)
    println(jsonString)
  }
}
/*
{"name":"Tom","address":{"city":"Talkeetna","state":"AK"}}
 */

/*
https://github.com/google/gson
 */
object GsonApp {
  case class Person(name: String, address: Address)
  case class Address(city: String, state: String)

  def main(args: Array[String]): Unit = {
    val person = Person("Tom", Address("Talkeetna", "AK"))

    // create a JSON string from the Person, then print it
    val gson = new Gson
    val jsonString = gson.toJson(person)
    println(jsonString)
  }
}
/*
{"name":"Tom","address":{"city":"Talkeetna","state":"AK"}}
 */

