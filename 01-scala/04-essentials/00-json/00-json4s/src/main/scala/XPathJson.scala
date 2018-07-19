import org.json4s.JObject
import org.json4s.JsonAST.{JField, JInt, JString}
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

object XPathJson {
  def main(args: Array[String]): Unit = {
    jsonTest

    indexedPath
  }

  private def jsonTest = {
    val json: JObject =
      ("person" ->
        ("name" -> "Joe") ~
          ("age" -> 35) ~
          ("spouse" ->
            ("person" ->
              ("name" -> "Marilyn") ~
                ("age" -> 33)
              )
            )
        )

    println(s"""# json \\\\ "spouse" """)
    val jValueSpouse = json \\ "spouse"
    println(s"${jValueSpouse.getClass.getSimpleName}: $jValueSpouse")
    val prettyStrSpouse = pretty(jValueSpouse)
    println(s"pretty ${prettyStrSpouse.getClass.getSimpleName}: $prettyStrSpouse")

    println(s"""# json \\\\ "name" """)
    val jValueName = json \\ "name"
    println(s"${jValueName.getClass.getSimpleName}: $jValueName")
    val prettyStrName = pretty(jValueName)
    println(s"pretty ${prettyStrName.getClass.getSimpleName}: $prettyStrName")

    println(s"""# json \\ "person" \\ "name" """)
    val jValuePersonName = json \ "person" \ "name"
    println(s"${jValuePersonName.getClass.getSimpleName}: $jValuePersonName")
    val prettyStrPersonName = pretty(jValuePersonName)
    println(s"pretty ${prettyStrPersonName.getClass.getSimpleName}: $prettyStrPersonName")

    println(s"""# json \\ person \\ spouse \\ "person" \\ "name" """)
    val jValuePersonSpousePersonName = json \ "person" \ "spouse" \ "person" \ "name"
    println(s"${jValuePersonSpousePersonName.getClass.getSimpleName}: $jValuePersonSpousePersonName")
    val prettyStrPersonSpousePersonName = pretty(jValuePersonSpousePersonName)
    println(s"pretty ${prettyStrPersonSpousePersonName.getClass.getSimpleName}: $prettyStrPersonSpousePersonName")

    println(s"""# json filterField {} """)
    val jFields = json filterField {
      case JField("name", _) => true
      case _ => false
    }
    println(s"${jFields.getClass.getSimpleName}: $jFields")
    val prettyStrNames = pretty(jFields)
    println(s"pretty ${prettyStrNames.getClass.getSimpleName}: $prettyStrNames")

    println(s"""# json transformField {} """)
    val jValueTransformField = json transformField {
      case JField("name", JString(s)) => ("NAME", JString(s.toUpperCase))
    }
    println(jValueTransformField)
    println(s"${jValueTransformField.getClass.getSimpleName}: $jValueTransformField")
    val prettyStrTransformField = pretty(jValueTransformField)
    println(s"pretty ${prettyStrTransformField.getClass.getSimpleName}: $prettyStrTransformField")

    println("# Map - json.values")
    println(s"json.values: ${json.values}")
  }
/*
# json \\ "spouse"
JObject: JObject(List((person,JObject(List((name,JString(Marilyn)), (age,JInt(33)))))))
pretty String: {
  "person" : {
    "name" : "Marilyn",
    "age" : 33
  }
}
# json \\ "name"
JObject: JObject(List((name,JString(Joe)), (name,JString(Marilyn))))
pretty String: {
  "name" : "Joe",
  "name" : "Marilyn"
}
# json \ "person" \ "name"
JString: JString(Joe)
pretty String: "Joe"
# json \ person \ spouse \ "person" \ "name"
JString: JString(Marilyn)
pretty String: "Marilyn"
# json filterField {}
$colon$colon: List((name,JString(Joe)), (name,JString(Marilyn)))
pretty String: {
  "name" : "Joe",
  "name" : "Marilyn"
}
# json transformField {}
JObject(List((person,JObject(List((NAME,JString(JOE)), (age,JInt(35)), (spouse,JObject(List((person,JObject(List((NAME,JString(MARILYN)), (age,JInt(33)))))))))))))
JObject: JObject(List((person,JObject(List((NAME,JString(JOE)), (age,JInt(35)), (spouse,JObject(List((person,JObject(List((NAME,JString(MARILYN)), (age,JInt(33)))))))))))))
pretty String: {
  "person" : {
    "NAME" : "JOE",
    "age" : 35,
    "spouse" : {
      "person" : {
        "NAME" : "MARILYN",
        "age" : 33
      }
    }
  }
}
# Map - json.values
json.values: Map(person -> Map(name -> Joe, age -> 35, spouse -> Map(person -> Map(name -> Marilyn, age -> 33))))
 */

  private def indexedPath = {
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

    val jValue0 = (json \ "children") (0)
    println(s"${jValue0.getClass.getSimpleName}: $jValue0")
    val prettyStr0 = pretty(jValue0)
    println(s"pretty ${prettyStr0.getClass.getSimpleName}: $prettyStr0")

    val jValue1Name = (json \ "children") (1) \ "name"
    println(s"${jValue1Name.getClass.getSimpleName}: $jValue1Name")
    val prettyStr1Name = pretty(jValue1Name)
    println(s"pretty ${prettyStr1Name.getClass.getSimpleName}: $prettyStr1Name")

    val ints = json \\ classOf[JInt]
    println(s"${ints.getClass.getSimpleName}: $ints")
    val prettyInts = pretty(ints)
    println(s"pretty ${prettyInts.getClass.getSimpleName}: $prettyInts")

    val strs = json \ "children" \\ classOf[JString]
    println(s"${strs.getClass.getSimpleName}: $strs")
    val prettyStrs = pretty(strs)
    println(s"pretty ${prettyStrs.getClass.getSimpleName}: $prettyStrs")
  }
/*
JObject: JObject(List((name,JString(Mary)), (age,JInt(5))))
pretty String: {
  "name" : "Mary",
  "age" : 5
}
JString: JString(Mazy)
pretty String: "Mazy"
$colon$colon: List(5, 3)
pretty String: [ 5, 3 ]
$colon$colon: List(Mary, Mazy)
pretty String: [ "Mary", "Mazy" ]
 */
}
