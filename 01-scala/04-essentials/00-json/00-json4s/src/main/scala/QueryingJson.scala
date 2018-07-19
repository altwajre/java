import org.json4s.JsonAST.{JField, JInt, JObject, JString}
import org.json4s.jackson.JsonMethods.pretty
import org.json4s.native.JsonMethods._

object QueryingJson {
  def main(args: Array[String]): Unit = {
    linqStyle

  }

  private def linqStyle = {
    val json = parse(
      """
         { "name": "joe",
           "children": [
             {
               "name": "Mary",
               "age": 5
             },
             {
               "name": "Mazy",
               "age": 3
             }
           ]
         }
       """)
    println(pretty(json))

    val ages = for {
      JObject(child) <- json
      JField("age", JInt(age)) <- child
    } yield age
    println(s"ages: $ages")

    val olderThan4 = for {
      JObject(child) <- json
      JField("name", JString(name)) <- child
      JField("age", JInt(age)) <- child
      if age > 4
    } yield (name, age)
    println(s"olderThan4: $olderThan4")
  }
}
/*
{
  "name" : "joe",
  "children" : [ {
    "name" : "Mary",
    "age" : 5
  }, {
    "name" : "Mazy",
    "age" : 3
  } ]
}
ages: List(5, 3)
olderThan4: List((Mary,5))
 */
