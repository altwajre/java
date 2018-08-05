import net.liftweb.json._
import net.liftweb.json.JsonDSL._

/*
Creating a JSON String from Classes That Have Collections

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch15s03.html
 */
object _02_CreateJsonFromFromClassHasCollections {
  case class Person(name: String, address: Address) {
    var friends = List[Person]()
  }
  case class Address(city: String, state: String)

  def main(args: Array[String]): Unit = {
    // import net.liftweb.json.JsonParser._
    implicit val formats = DefaultFormats

    val dick = Person("Dick", Address("Somewhere", "KY"))
    val harry = Person("Harry", Address("Lake", "IL"))
    val friends = List(dick, harry)

    val p = Person("Tom", Address("Seattle", "WA"))
    p.friends = friends

    val json =
      ("person" ->
        ("name" -> p.name) ~
          ("address" ->
            ("city" -> p.address.city) ~
              ("state" -> p.address.state)) ~
          ("friends" ->
            friends.map { f =>
              ("name" -> f.name) ~
                ("address" ->
                  ("city" -> f.address.city) ~
                    ("state" -> f.address.state))
            })
        )

//    println(json)
    println(prettyRender(json))
  }
}
/*
{
  "person":{
    "name":"Tom",
    "address":{
      "city":"Seattle",
      "state":"WA"
    },
    "friends":[
      {
        "name":"Dick",
        "address":{
          "city":"Somewhere",
          "state":"KY"
        }
      },
      {
        "name":"Harry",
        "address":{
          "city":"Lake",
          "state":"IL"
        }
      }
    ]
  }
}
 */
