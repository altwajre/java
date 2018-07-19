import org.json4s.Diff
import org.json4s.jackson.JsonMethods._
object MergingAndDiffing {
  def main(args: Array[String]): Unit = {
    val lotto1 = parse(
      """{
        |"lotto": {
        |  "lotto-id":5,
        |  "winning-numbers":[2,45,34,23,7,5,3],
        |  "winners":[{
        |    "winner-id":23,
        |    "numbers":[2,45,34,23,3,5]
        |  }]
        |}
      }""".stripMargin)
    println("# lotto1")
    println(lotto1)
    println(pretty(lotto1))

    val lotto2 = parse("""{
         "lotto":{
           "winners":[{
             "winner-id":54,
             "numbers":[52,3,12,11,18,22]
           }]
         }
       }""")
    println("# lotto2")
    println(lotto2)
    println(pretty(lotto2))

    val mergedLotto = lotto1 merge lotto2
    println("# lotto1 merge lotto2")
    println(mergedLotto)
    println(pretty(mergedLotto))

    val Diff(changed, added, deleted) =  mergedLotto diff lotto1
    println("# mergedLotto diff lotto1")
    println(" ## changed")
    println(changed)
    println(" ## added")
    println(added)
    println(" ## deleted")
    println(deleted)
    println(pretty(deleted))
  }
}
/*
# lotto1
JObject(List((lotto,JObject(List((lotto-id,JInt(5)), (winning-numbers,JArray(List(JInt(2), JInt(45), JInt(34), JInt(23), JInt(7), JInt(5), JInt(3)))), (winners,JArray(List(JObject(List((winner-id,JInt(23)), (numbers,JArray(List(JInt(2), JInt(45), JInt(34), JInt(23), JInt(3), JInt(5))))))))))))))
{
  "lotto" : {
    "lotto-id" : 5,
    "winning-numbers" : [ 2, 45, 34, 23, 7, 5, 3 ],
    "winners" : [ {
      "winner-id" : 23,
      "numbers" : [ 2, 45, 34, 23, 3, 5 ]
    } ]
  }
}
# lotto2
JObject(List((lotto,JObject(List((winners,JArray(List(JObject(List((winner-id,JInt(54)), (numbers,JArray(List(JInt(52), JInt(3), JInt(12), JInt(11), JInt(18), JInt(22))))))))))))))
{
  "lotto" : {
    "winners" : [ {
      "winner-id" : 54,
      "numbers" : [ 52, 3, 12, 11, 18, 22 ]
    } ]
  }
}
# lotto1 merge lotto2
JObject(List((lotto,JObject(List((lotto-id,JInt(5)), (winning-numbers,JArray(List(JInt(2), JInt(45), JInt(34), JInt(23), JInt(7), JInt(5), JInt(3)))), (winners,JArray(List(JObject(List((winner-id,JInt(23)), (numbers,JArray(List(JInt(2), JInt(45), JInt(34), JInt(23), JInt(3), JInt(5)))))), JObject(List((winner-id,JInt(54)), (numbers,JArray(List(JInt(52), JInt(3), JInt(12), JInt(11), JInt(18), JInt(22))))))))))))))
{
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
# mergedLotto diff lotto1
 ## changed
JNothing
 ## added
JNothing
 ## deleted
JObject(List((lotto,JObject(List((winners,JArray(List(JObject(List((winner-id,JInt(54)), (numbers,JArray(List(JInt(52), JInt(3), JInt(12), JInt(11), JInt(18), JInt(22))))))))))))))
{
  "lotto" : {
    "winners" : [ {
      "winner-id" : 54,
      "numbers" : [ 52, 3, 12, 11, 18, 22 ]
    } ]
  }
}
 */
