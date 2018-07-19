import java.util.Date

import org.json4s._
import org.json4s.jackson.JsonMethods._

object ExtractingValues {
  implicit val formats = DefaultFormats // Brings in default date formats etc.

  case class Child(name: String, age: Int, birthdate: Option[Date])
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address, children: List[Child])

  def main(args: Array[String]): Unit = {
    val json = parse("""
         { "name": "joe",
           "address": {
             "street": "Bulevard",
             "city": "Helsinki"
           },
           "children": [
             {
               "name": "Mary",
               "age": 5,
               "birthdate": "2004-09-04T18:06:22Z"
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)
    val person = json.extract[Person]
    println(person)

    val addressJson = json \ "address" // Extract address object
    val address = addressJson.extract[Address]
    println(address)

    val children = (json \ "children").extract[List[Child]]
    println(children)
  }
}
/*
Person(joe,Address(Bulevard,Helsinki),List(Child(Mary,5,Some(Sat Sep 04 11:06:22 PDT 2004)), Child(Mazy,3,None)))
Address(Bulevard,Helsinki)
List(Child(Mary,5,Some(Sat Sep 04 11:06:22 PDT 2004)), Child(Mazy,3,None))
 */
