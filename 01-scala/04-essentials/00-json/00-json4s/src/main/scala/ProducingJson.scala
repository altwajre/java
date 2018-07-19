import java.util.Date

import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._

object ProducingJson {

  case class Winner(id: Long, numbers: List[Int])

  case class Lotto(id: Long, winningNumbers: List[Int], winners: List[Winner], drawDate: Option[Date])

  def main(args: Array[String]): Unit = {
    listJsonTest

    tupleJsonTest

    jObjectJsonTest

    classJsonTest
  }

  private def classJsonTest = {
    println("# classJsonTest")
    val winners = List(Winner(23, List(2, 45, 34, 23, 3, 5)), Winner(54, List(52, 3, 12, 11, 18, 22)))
    val lotto = Lotto(5, List(2, 45, 34, 23, 7, 5, 3), winners, None)

    val json =
      ("lotto" ->
        ("lotto-id" -> lotto.id) ~
          ("winning-numbers" -> lotto.winningNumbers) ~
          ("draw-date" -> lotto.drawDate.map(_.toString)) ~
          ("winners" ->
            lotto.winners.map { w =>
              (("winner-id" -> w.id) ~
                ("numbers" -> w.numbers))
            }))

    val jValue = render(json)
    println(s"${jValue.getClass.getSimpleName}: $jValue")
    val compactStrJson = compact(jValue)
    println(s"compact ${compactStrJson.getClass.getSimpleName}: $compactStrJson")

    val prettyStrJson = pretty(jValue)
    println(s"pretty ${prettyStrJson.getClass.getSimpleName}: $prettyStrJson")

  }
  /*
# classJsonTest
JObject: JObject(List((lotto,JObject(List((lotto-id,JInt(5)), (winning-numbers,JArray(List(JInt(2), JInt(45), JInt(34), JInt(23), JInt(7), JInt(5), JInt(3)))), (draw-date,JNothing), (winners,JArray(List(JObject(List((winner-id,JInt(23)), (numbers,JArray(List(JInt(2), JInt(45), JInt(34), JInt(23), JInt(3), JInt(5)))))), JObject(List((winner-id,JInt(54)), (numbers,JArray(List(JInt(52), JInt(3), JInt(12), JInt(11), JInt(18), JInt(22))))))))))))))
compact String: {"lotto":{"lotto-id":5,"winning-numbers":[2,45,34,23,7,5,3],"winners":[{"winner-id":23,"numbers":[2,45,34,23,3,5]},{"winner-id":54,"numbers":[52,3,12,11,18,22]}]}}
pretty String: {
  "lotto" : {
    "lotto-id" : 5,
    "winning-numbers" : [ 2, 45, 34, 23, 7, 5, 3 ],
    "winners" : [ {
      "winner-id" : 23,
      "numbers" : [ 2, 45, 34, 23, 3, 5 ]
    }, {
      "winner-id" : 54,
      "numbers" : [ 52, 3, 12, 11, 18, 22 ]
    } ]
  }
}
   */

  private def jObjectJsonTest = {
    println("# jObjectJsonTest")
    val jObjectJson = ("name" -> "joe") ~ ("age" -> 28)
    println(" ## JObject")
    println(s"${jObjectJson.getClass.getSimpleName}: $jObjectJson")
    println(" ## JObject json")
    val jValue = render(jObjectJson)
    println(s"${jValue.getClass.getSimpleName}: $jValue")
    val strJson = compact(jValue)
    println(s"${strJson.getClass.getSimpleName}: $strJson")
  }
  /*
# jObjectJsonTest
 ## JObject
JObject: JObject(List((name,JString(joe)), (age,JInt(28))))
 ## JObject json
JObject: JObject(List((name,JString(joe)), (age,JInt(28))))
String: {"name":"joe","age":28}
   */

  private def tupleJsonTest = {
    println("# tupleJsonTest")
    val tupleJson = ("name" -> "joe")
    println(" ## Tuple2")
    println(s"${tupleJson.getClass.getSimpleName}: $tupleJson")
    println(" ## Tuple2 json")
    val jValue = render(tupleJson)
    println(s"${jValue.getClass.getSimpleName}: $jValue")
    val strJson = compact(jValue)
    println(s"${strJson.getClass.getSimpleName}: $strJson")
  }
  /*
# tupleJsonTest
 ## Tuple2
Tuple2: (name,joe)
 ## Tuple2 json
JObject: JObject(List((name,JString(joe))))
String: {"name":"joe"}
   */

  private def listJsonTest = {
    println("# listJsonTest")
    val listJson = List(1, 2, 3)
    println(" ## list")
    println(s"${listJson.getClass.getSimpleName}: $listJson")
    println(" ## list json")
    val jValue = render(listJson)
    println(s"${jValue.getClass.getSimpleName}: $jValue")
    val strJson = compact(jValue)
    println(s"${strJson.getClass.getSimpleName}: $strJson")
  }
  /*
# listJsonTest
 ## list
$colon$colon: List(1, 2, 3)
 ## list json
JArray: JArray(List(JInt(1), JInt(2), JInt(3)))
String: [1,2,3]
   */
}
